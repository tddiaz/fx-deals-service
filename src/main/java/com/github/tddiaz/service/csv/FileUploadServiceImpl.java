package com.github.tddiaz.service.csv;

import com.github.tddiaz.domain.CurrencyCode;
import com.github.tddiaz.domain.InvalidDeal;
import com.github.tddiaz.domain.TransactionLog;
import com.github.tddiaz.domain.ValidDeal;
import com.github.tddiaz.event.DealsImportedEvent;
import com.github.tddiaz.exception.FileUploadException;
import com.github.tddiaz.service.deals.AccumulativeDealCountService;
import com.github.tddiaz.service.deals.DealService;
import com.github.tddiaz.service.deals.DealsValidator;
import com.github.tddiaz.service.dto.DealDto;
import org.apache.commons.csv.CSVRecord;
import org.apache.commons.lang3.time.StopWatch;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 * Created by tristandiaz on 11/3/17.
 */
@Service("fileUploadService")
public class FileUploadServiceImpl implements FileUploadService {

    private static final Logger LOGGER = LoggerFactory.getLogger(FileUploadServiceImpl.class);

    private DealsValidator dealsValidator;

    private DealService dealService;

    private AccumulativeDealCountService accumulativeDealCountService;

    private CSVFileParser csvFileParser;

    private ApplicationEventPublisher applicationEventPublisher;

    @Autowired
    public void setApplicationEventPublisher(ApplicationEventPublisher applicationEventPublisher) {
        this.applicationEventPublisher = applicationEventPublisher;
    }

    @Autowired
    public void setCsvFileParser(CSVFileParser csvFileParser) {
        this.csvFileParser = csvFileParser;
    }

    @Autowired
    public void setAccumulativeDealCountService(AccumulativeDealCountService accumulativeDealCountService) {
        this.accumulativeDealCountService = accumulativeDealCountService;
    }

    @Autowired
    public void setDealService(DealService dealService) {
        this.dealService = dealService;
    }

    @Autowired
    public void setDealsValidator(DealsValidator dealsValidator) {
        this.dealsValidator = dealsValidator;
    }

    @Override
    @Transactional
    public void uploadFile(MultipartFile file, TransactionLog transactionLog) {

        LOGGER.info("Import File Request. File Name: {}", transactionLog.getFileName());

        try {

            final StopWatch stopWatch = new StopWatch();
            stopWatch.start();

            final Iterable<CSVRecord> records = csvFileParser.parse(file);

            final Map<CurrencyCode, Long> currencyCountMap = accumulativeDealCountService.findAllDealsCurrencyCountMap();

            final List<ValidDeal> validDeals = new LinkedList<>();
            final List<InvalidDeal> invalidDeals = new LinkedList<>();


            for (CSVRecord record: records) {

                final DealDto dealDto = new DealDto();
                dealDto.setDealId(record.get(Headers.DEAL_ID));
                dealDto.setFromCurrency(record.get(Headers.FROM_CURRENCY));
                dealDto.setToCurrency(record.get(Headers.TO_CURRENCY));
                dealDto.setDateTime(record.get(Headers.TIMESTAMP));
                dealDto.setAmount(record.get(Headers.AMOUNT));

                if (dealsValidator.valid(dealDto)) {

                    final ValidDeal validDeal = ValidDeal.valueOf(dealDto);
                    validDeal.setFileName(transactionLog.getFileName());

                    validDeals.add(validDeal);

                    incrementCurrencyCount(currencyCountMap, CurrencyCode.valueOf(dealDto.getFromCurrency()));

                } else {

                    final InvalidDeal invalidDeal = InvalidDeal.valueOf(dealDto);
                    invalidDeal.setFileName(transactionLog.getFileName());

                    invalidDeals.add(invalidDeal);
                }
            }


            dealService.saveValidDeals(validDeals);
            dealService.saveInvalidDeals(invalidDeals);

            stopWatch.stop();

            final double processDuration = Double.valueOf(stopWatch.getTime()) / 1000;

            LOGGER.info("Deals Imported Summary: Number of Valid Deals - {}, Number of Invalid Deals - {}, Elapsed Time - {}s", validDeals.size(), invalidDeals.size(), processDuration);

            transactionLog.setInvalidDealsImportedCount(invalidDeals.size());
            transactionLog.setDealsImportedCount(validDeals.size());
            transactionLog.setProcessDuration(processDuration);

            applicationEventPublisher.publishEvent(new DealsImportedEvent(currencyCountMap, transactionLog));

        } catch (Exception e) {
            throw new FileUploadException("Error importing deals.", e);
        }
    }

    private void incrementCurrencyCount(Map<CurrencyCode, Long> currencyCountMap, CurrencyCode code) {
        currencyCountMap.merge(code, 1L, Long::sum);
    }

    private enum Headers {
        DEAL_ID,
        FROM_CURRENCY,
        TO_CURRENCY,
        TIMESTAMP,
        AMOUNT
    }
}

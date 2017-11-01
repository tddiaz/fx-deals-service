package com.github.tddiaz.service.deals;

import com.github.tddiaz.domain.CurrencyCode;
import com.github.tddiaz.domain.InvalidDeal;
import com.github.tddiaz.domain.TransactionLog;
import com.github.tddiaz.domain.ValidDeal;
import com.github.tddiaz.event.DealsImportedEvent;
import com.github.tddiaz.repository.InvalidDealRepository;
import com.github.tddiaz.repository.ValidDealRepository;
import com.github.tddiaz.service.dto.DealDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by tristandiaz on 10/29/17.
 */
@Service("dealService")
public class DealServiceImpl implements DealService {

    private static final Logger LOGGER = LoggerFactory.getLogger(DealServiceImpl.class);

    private InvalidDealRepository invalidDealRepository;

    private ValidDealRepository validDealRepository;

    private DealsValidator dealsValidator;

    private AccumulativeDealCountService accumulativeDealCountService;

    private ApplicationEventPublisher applicationEventPublisher;

    @Autowired
    public void setApplicationEventPublisher(ApplicationEventPublisher applicationEventPublisher) {
        this.applicationEventPublisher = applicationEventPublisher;
    }

    @Autowired
    public void setAccumulativeDealCountService(AccumulativeDealCountService accumulativeDealCountService) {
        this.accumulativeDealCountService = accumulativeDealCountService;
    }

    @Autowired
    public void setDealsValidator(DealsValidator dealsValidator) {
        this.dealsValidator = dealsValidator;
    }

    @Autowired
    public void setInvalidDealRepository(InvalidDealRepository invalidDealRepository) {
        this.invalidDealRepository = invalidDealRepository;
    }

    @Autowired
    public void setValidDealRepository(ValidDealRepository validDealRepository) {
        this.validDealRepository = validDealRepository;
    }

    @Override
    @Transactional
    public void batchSave(List<DealDto> dealDtos, TransactionLog transactionLog) {

        LOGGER.info("Importing Deals. Number of entries: {}", dealDtos.size());

        final Map<CurrencyCode, Long> currencyCountMap = accumulativeDealCountService.findAllDealsCurrencyCountMap();

        final List<ValidDeal> validDeals = new ArrayList<>(dealDtos.size());
        final List<InvalidDeal> invalidDeals = new ArrayList<>();

        dealDtos.forEach(dealDto -> {
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
        });

        validDealRepository.save(validDeals);
        invalidDealRepository.save(invalidDeals);

        LOGGER.info("Deals Imported Summary: Number of Valid Deals - {}, Number of Invalid Deals - {}", validDeals.size(), invalidDeals.size());

        transactionLog.setDealsImportedCount(validDeals.size());
        transactionLog.setInvalidDealsImportedCount(invalidDeals.size());

        applicationEventPublisher.publishEvent(new DealsImportedEvent(currencyCountMap, transactionLog));
    }

    private void incrementCurrencyCount(Map<CurrencyCode, Long> currencyCountMap, CurrencyCode code) {
        currencyCountMap.merge(code, 1L, Long::sum);
    }

    @Override
    public ValidDeal findValidDealByDealId(String dealId) {
        return validDealRepository.findByDealId(dealId);
    }
}

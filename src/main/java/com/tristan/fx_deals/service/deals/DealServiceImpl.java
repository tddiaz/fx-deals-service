package com.tristan.fx_deals.service.deals;

import com.tristan.fx_deals.domain.CurrencyCode;
import com.tristan.fx_deals.domain.InvalidDeal;
import com.tristan.fx_deals.domain.ValidDeal;
import com.tristan.fx_deals.event.DealsImportedEvent;
import com.tristan.fx_deals.repository.InvalidDealRepository;
import com.tristan.fx_deals.repository.ValidDealRepository;
import com.tristan.fx_deals.service.dto.DealDto;
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
    public void batchSave(List<DealDto> dealDtos) {

        final Map<CurrencyCode, Long> currencyCountMap = accumulativeDealCountService.findAllDealsCurrencyCountMap();

        final List<ValidDeal> validDeals = new ArrayList<>(dealDtos.size());
        final List<InvalidDeal> invalidDeals = new ArrayList<>();

        dealDtos.forEach(dealDto -> {
            if (dealsValidator.valid(dealDto)) {
                validDeals.add(ValidDeal.valueOf(dealDto));
                incrementCurrencyCount(currencyCountMap, CurrencyCode.valueOf(dealDto.getFromCurrency()));
            } else {
                invalidDeals.add(InvalidDeal.valueOf(dealDto));
            }
        });

        validDealRepository.save(validDeals);
        invalidDealRepository.save(invalidDeals);

        applicationEventPublisher.publishEvent(new DealsImportedEvent(currencyCountMap));
    }

    private void incrementCurrencyCount(Map<CurrencyCode, Long> currencyCountMap, CurrencyCode code) {
        currencyCountMap.merge(code, 1L, Long::sum);
    }

    @Override
    public ValidDeal findValidDealByDealId(String dealId) {
        return validDealRepository.findByDealId(dealId);
    }
}

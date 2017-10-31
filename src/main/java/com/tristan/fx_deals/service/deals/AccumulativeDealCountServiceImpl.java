package com.tristan.fx_deals.service.deals;

import com.tristan.fx_deals.domain.AccumulativeDealCount;
import com.tristan.fx_deals.domain.CurrencyCode;
import com.tristan.fx_deals.repository.AccumulativeDealCountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by tristandiaz on 10/29/17.
 */
@Service("accumulativeDealCountService")
public class AccumulativeDealCountServiceImpl implements AccumulativeDealCountService {

    @Value("${accumulative_deal_counts.initialize}")
    private boolean initializeValues;

    private AccumulativeDealCountRepository accumulativeDealCountRepository;

    @Autowired
    public void setAccumulativeDealCountRepository(AccumulativeDealCountRepository accumulativeDealCountRepository) {
        this.accumulativeDealCountRepository = accumulativeDealCountRepository;
    }

    @PostConstruct
    public void init() {

        if (initializeValues) {
            final List<AccumulativeDealCount> accumulativeDealCounts = new ArrayList<>(CurrencyCode.values().length);

            for (CurrencyCode currencyCode : CurrencyCode.values()) {
                accumulativeDealCounts.add(new AccumulativeDealCount(currencyCode));
            }

            accumulativeDealCountRepository.save(accumulativeDealCounts);
        }
    }

    @Override
    @Transactional
    public void updateCountOfDealsPerCurrency(Map<CurrencyCode, Long> currencyCountMap) {

        final List<AccumulativeDealCount> accumulativeDealCounts = accumulativeDealCountRepository.findAll();

        accumulativeDealCounts.forEach(accumulativeDealCount -> {
            accumulativeDealCount.setCountOfDeals(currencyCountMap.get(accumulativeDealCount.getCurrencyCode()));
        });

        accumulativeDealCountRepository.save(accumulativeDealCounts);
    }

    @Override
    public Map<CurrencyCode, Long> findAllDealsCurrencyCountMap() {
        return accumulativeDealCountRepository.findAllCurrencyCountMap();
    }
}

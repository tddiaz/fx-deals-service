package com.github.tddiaz.service.deals;

import com.github.tddiaz.domain.CurrencyCode;

import java.util.Map;

/**
 * Created by tristandiaz on 10/29/17.
 */
public interface AccumulativeDealCountService {

    void updateCountOfDealsPerCurrency(Map<CurrencyCode, Long> currencyCountMap);

    Map<CurrencyCode, Long> findAllDealsCurrencyCountMap();

}

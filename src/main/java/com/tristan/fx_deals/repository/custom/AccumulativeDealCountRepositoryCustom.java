package com.tristan.fx_deals.repository.custom;

import com.tristan.fx_deals.domain.CurrencyCode;

import java.util.Map;

/**
 * Created by tristandiaz on 11/1/17.
 */
public interface AccumulativeDealCountRepositoryCustom {

    Map<CurrencyCode, Long> findAllCurrencyCountMap();
}

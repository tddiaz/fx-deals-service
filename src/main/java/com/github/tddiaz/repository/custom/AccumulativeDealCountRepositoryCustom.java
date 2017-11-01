package com.github.tddiaz.repository.custom;

import com.github.tddiaz.domain.CurrencyCode;

import java.util.Map;

/**
 * Created by tristandiaz on 11/1/17.
 */
public interface AccumulativeDealCountRepositoryCustom {

    Map<CurrencyCode, Long> findAllCurrencyCountMap();
}

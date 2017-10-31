package com.tristan.fx_deals.event;

import com.tristan.fx_deals.domain.CurrencyCode;
import lombok.Getter;
import lombok.Setter;

import java.util.Map;

/**
 * Created by tristandiaz on 10/31/17.
 */
@Getter
@Setter
public class DealsImportedEvent {

    private Map<CurrencyCode, Long> currencyCountMap;

    public DealsImportedEvent(Map<CurrencyCode, Long> currencyCountMap) {
        this.currencyCountMap = currencyCountMap;
    }
}

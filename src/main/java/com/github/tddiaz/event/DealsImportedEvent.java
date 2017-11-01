package com.github.tddiaz.event;

import com.github.tddiaz.domain.TransactionLog;
import com.github.tddiaz.domain.CurrencyCode;
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

    private TransactionLog transactionLog;

    public DealsImportedEvent(Map<CurrencyCode, Long> currencyCountMap, TransactionLog transactionLog) {
        this.currencyCountMap = currencyCountMap;
        this.transactionLog = transactionLog;
    }
}

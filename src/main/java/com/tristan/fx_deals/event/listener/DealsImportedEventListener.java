package com.tristan.fx_deals.event.listener;

import com.tristan.fx_deals.event.DealsImportedEvent;
import com.tristan.fx_deals.service.deals.AccumulativeDealCountService;
import com.tristan.fx_deals.service.logging.TransactionLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

/**
 * Created by tristandiaz on 10/31/17.
 */
@Component
public class DealsImportedEventListener {

    private AccumulativeDealCountService accumulativeDealCountService;

    private TransactionLogService transactionLogService;

    @Autowired
    public void setTransactionLogService(TransactionLogService transactionLogService) {
        this.transactionLogService = transactionLogService;
    }

    @Autowired
    public void setAccumulativeDealCountService(AccumulativeDealCountService accumulativeDealCountService) {
        this.accumulativeDealCountService = accumulativeDealCountService;
    }

    @EventListener
    public void execute(DealsImportedEvent event) {
        accumulativeDealCountService.updateCountOfDealsPerCurrency(event.getCurrencyCountMap());
        transactionLogService.save(event.getTransactionLog());
    }
}

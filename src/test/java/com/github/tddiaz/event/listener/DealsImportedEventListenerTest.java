package com.github.tddiaz.event.listener;

import com.github.tddiaz.domain.TransactionLog;
import com.github.tddiaz.domain.CurrencyCode;
import com.github.tddiaz.event.DealsImportedEvent;
import com.github.tddiaz.service.deals.AccumulativeDealCountService;
import com.github.tddiaz.service.logging.TransactionLogService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by tristandiaz on 11/1/17.
 */
@RunWith(MockitoJUnitRunner.class)
public class DealsImportedEventListenerTest {

    @Mock
    private AccumulativeDealCountService accumulativeDealCountService;

    @Mock
    private TransactionLogService transactionLogService;

    @InjectMocks
    private DealsImportedEventListener dealsImportedEventListener = new DealsImportedEventListener();

    @Test
    public void testExecute() {

        Map<CurrencyCode, Long> currencyCountMap = new HashMap<>();
        TransactionLog transactionLog = new TransactionLog();
        DealsImportedEvent event = new DealsImportedEvent(currencyCountMap, transactionLog);

        dealsImportedEventListener.execute(event);

        Mockito.verify(accumulativeDealCountService, Mockito.atLeastOnce()).updateCountOfDealsPerCurrency(Mockito.eq(currencyCountMap));
        Mockito.verify(transactionLogService, Mockito.atLeastOnce()).save(Mockito.eq(transactionLog));

    }
}

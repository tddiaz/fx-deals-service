package com.tristan.fx_deals.event.listener;

import com.tristan.fx_deals.domain.CurrencyCode;
import com.tristan.fx_deals.event.DealsImportedEvent;
import com.tristan.fx_deals.service.deals.AccumulativeDealCountService;
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

    @InjectMocks
    private DealsImportedEventListener dealsImportedEventListener = new DealsImportedEventListener();

    @Test
    public void testExecute() {

        Map<CurrencyCode, Long> currencyCountMap = new HashMap<>();
        DealsImportedEvent event = new DealsImportedEvent(currencyCountMap);

        dealsImportedEventListener.execute(event);

        Mockito.verify(accumulativeDealCountService, Mockito.atLeastOnce()).updateCountOfDealsPerCurrency(Mockito.eq(currencyCountMap));

    }
}

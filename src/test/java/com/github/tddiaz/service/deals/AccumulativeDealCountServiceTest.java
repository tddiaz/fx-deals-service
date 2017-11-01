package com.github.tddiaz.service.deals;

import com.github.tddiaz.domain.AccumulativeDealCount;
import com.github.tddiaz.domain.CurrencyCode;
import com.github.tddiaz.repository.AccumulativeDealCountRepository;
import static org.hamcrest.Matchers.*;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.mockito.Mockito.*;
import static org.junit.Assert.*;

/**
 * Created by tristandiaz on 11/1/17.
 */
@RunWith(MockitoJUnitRunner.class)
public class AccumulativeDealCountServiceTest {

    @Mock
    private AccumulativeDealCountRepository accumulativeDealCountRepository;

    @InjectMocks
    private AccumulativeDealCountService accumulativeDealCountService = new AccumulativeDealCountServiceImpl();

    @Test
    public void testFindAllDealsCurrencyCountMap() {

        Map<CurrencyCode, Long> map = new HashMap<>();

        when(accumulativeDealCountRepository.findAllCurrencyCountMap()).thenReturn(map);

        Map<CurrencyCode, Long> result = accumulativeDealCountService.findAllDealsCurrencyCountMap();

        verify(accumulativeDealCountRepository, atLeastOnce()).findAllCurrencyCountMap();
        assertThat(result, is(map));
    }

    @Test
    public void testUpdateCountOfDealsPerCurrency() {

        Map<CurrencyCode, Long> currencyCountMap = new HashMap<>(2);
        currencyCountMap.put(CurrencyCode.AED, 5L);
        currencyCountMap.put(CurrencyCode.USD, 10L);

        AccumulativeDealCount accumulativeDealCount1 = new AccumulativeDealCount(CurrencyCode.AED);
        AccumulativeDealCount accumulativeDealCount2 = new AccumulativeDealCount(CurrencyCode.USD);

        List<AccumulativeDealCount> accumulativeDealCounts = new ArrayList<>(2);
        accumulativeDealCounts.add(accumulativeDealCount1);
        accumulativeDealCounts.add(accumulativeDealCount2);

        when(accumulativeDealCountRepository.findAll()).thenReturn(accumulativeDealCounts);

        accumulativeDealCountService.updateCountOfDealsPerCurrency(currencyCountMap);

        verify(accumulativeDealCountRepository, atLeastOnce()).findAll();

        assertThat(accumulativeDealCounts, hasItems(
                hasProperty("countOfDeals", is(5L)),
                hasProperty("countOfDeals", is(10L))
                ));

    }

}

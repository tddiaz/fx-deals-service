package com.tristan.fx_deals.service.deals;

import com.tristan.fx_deals.domain.CurrencyCode;
import com.tristan.fx_deals.domain.InvalidDeal;
import com.tristan.fx_deals.domain.TransactionLog;
import com.tristan.fx_deals.domain.ValidDeal;
import com.tristan.fx_deals.event.DealsImportedEvent;
import com.tristan.fx_deals.repository.InvalidDealRepository;
import com.tristan.fx_deals.repository.ValidDealRepository;
import com.tristan.fx_deals.service.dto.DealDto;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.context.ApplicationEventPublisher;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Matchers.anyString;
import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.anyListOf;
import static org.mockito.Mockito.atLeastOnce;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * Created by tristandiaz on 10/29/17.
 */
@RunWith(MockitoJUnitRunner.class)
public class DealServiceTest {

    @Mock
    private ValidDealRepository validDealRepository;

    @Mock
    private InvalidDealRepository invalidDealRepository;

    @Mock
    private DealsValidator dealsValidator;

    @Mock
    private AccumulativeDealCountService accumulativeDealCountService;

    @Mock
    private ApplicationEventPublisher applicationEventPublisher;

    @InjectMocks
    private DealService dealService = new DealServiceImpl();

    @Test
    public void testBatchSave() {

        Map<CurrencyCode, Long> currencyCountMap = new HashMap<>(2);
        currencyCountMap.put(CurrencyCode.AED, 0L);
        currencyCountMap.put(CurrencyCode.USD, 4L);

        DealDto deal1 = new DealDto();
        deal1.setFromCurrency("AED");
        deal1.setToCurrency("AED");
        deal1.setAmount("0");

        DealDto deal2 = new DealDto();
        deal2.setFromCurrency("AED");
        deal2.setToCurrency("AED");
        deal2.setAmount("0");

        DealDto deal3 = new DealDto();
        deal3.setFromCurrency("USD");
        deal3.setToCurrency("USD");
        deal3.setAmount("0");

        List<DealDto> dealDtos = new ArrayList<>(3);
        dealDtos.add(deal1);
        dealDtos.add(deal2);
        dealDtos.add(deal3);

        TransactionLog transactionLog = new TransactionLog();

        when(accumulativeDealCountService.findAllDealsCurrencyCountMap()).thenReturn(currencyCountMap);
        when(dealsValidator.valid(any(DealDto.class))).thenReturn(true);

        dealService.batchSave(dealDtos, transactionLog);

        verify(accumulativeDealCountService, atLeastOnce()).findAllDealsCurrencyCountMap();
        verify(dealsValidator, times(3)).valid(any(DealDto.class));
        verify(validDealRepository, atLeastOnce()).save(anyListOf(ValidDeal.class));
        verify(invalidDealRepository, atLeastOnce()).save(anyListOf(InvalidDeal.class));
        verify(applicationEventPublisher, atLeastOnce()).publishEvent(any(DealsImportedEvent.class));

        assertThat(currencyCountMap.get(CurrencyCode.AED), is(2L));
        assertThat(currencyCountMap.get(CurrencyCode.USD), is(5L));
        assertThat(transactionLog.getDealsImportedCount(), is(3));
        assertThat(transactionLog.getInvalidDealsImportedCount(), is(0));
    }

    @Test
    public void testFIndValidDealByDealId() {

        ValidDeal testDeal = new ValidDeal();

        when(validDealRepository.findByDealId(anyString())).thenReturn(testDeal);

        ValidDeal returnedDeal = dealService.findValidDealByDealId("deal_id");

        verify(validDealRepository, atLeastOnce()).findByDealId(eq("deal_id"));
        assertThat(returnedDeal, is(testDeal));
    }

}

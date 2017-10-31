package com.tristan.fx_deals.service.deals;

import com.tristan.fx_deals.domain.InvalidDeal;
import com.tristan.fx_deals.domain.ValidDeal;
import com.tristan.fx_deals.repository.InvalidDealRepository;
import com.tristan.fx_deals.repository.ValidDealRepository;
import com.tristan.fx_deals.service.deals.DealService;
import com.tristan.fx_deals.service.deals.DealServiceImpl;
import com.tristan.fx_deals.service.deals.DealsValidator;
import com.tristan.fx_deals.service.dto.DealDto;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Matchers.anyString;
import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.anyListOf;
import static org.mockito.Mockito.atLeastOnce;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import static org.junit.Assert.*;
import static org.hamcrest.Matchers.*;

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

    @InjectMocks
    private DealService dealService = new DealServiceImpl();

    @Test
    public void testBatchSave() {

        List<DealDto> dealDtos = new ArrayList<>(4);
        dealDtos.add(new DealDto());
        dealDtos.add(new DealDto());
        dealDtos.add(new DealDto());
        dealDtos.add(new DealDto());

        dealService.batchSave(dealDtos);

        verify(dealsValidator, times(4)).valid(any(DealDto.class));
        verify(validDealRepository, atLeastOnce()).save(anyListOf(ValidDeal.class));
        verify(invalidDealRepository, atLeastOnce()).save(anyListOf(InvalidDeal.class));

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

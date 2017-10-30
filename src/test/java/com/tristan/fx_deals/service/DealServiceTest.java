package com.tristan.fx_deals.service;

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

import static org.mockito.Mockito.any;
import static org.mockito.Mockito.anyListOf;
import static org.mockito.Mockito.atLeastOnce;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

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

}

package com.tristan.fx_deals.service;

import com.tristan.fx_deals.domain.InvalidDeal;
import com.tristan.fx_deals.domain.ValidDeal;
import com.tristan.fx_deals.repository.InvalidDealRepository;
import com.tristan.fx_deals.repository.ValidDealRepository;
import com.tristan.fx_deals.service.impl.DealServiceImpl;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.*;

/**
 * Created by tristandiaz on 10/29/17.
 */
@RunWith(MockitoJUnitRunner.class)
public class DealServiceTest {

    @Mock
    private ValidDealRepository validDealRepository;

    @Mock
    private InvalidDealRepository invalidDealRepository;

    @InjectMocks
    private DealService dealService = new DealServiceImpl();

    @Test
    public void testBatchSave() {

        List<ValidDeal> validDeals = new ArrayList<>();
        List<InvalidDeal> invalidDeals = new ArrayList<>();

        dealService.batchSave(validDeals, invalidDeals);

        verify(validDealRepository, atLeastOnce()).save(eq(validDeals));
        verify(invalidDealRepository, atLeastOnce()).save(eq(invalidDeals));
    }

}

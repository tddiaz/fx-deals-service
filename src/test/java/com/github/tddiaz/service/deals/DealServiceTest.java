package com.github.tddiaz.service.deals;

import com.github.tddiaz.domain.InvalidDeal;
import com.github.tddiaz.domain.ValidDeal;
import com.github.tddiaz.repository.InvalidDealRepository;
import com.github.tddiaz.repository.ValidDealRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Matchers.anyString;
import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.atLeastOnce;
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

    @InjectMocks
    private DealService dealService = new DealServiceImpl();

    @Test
    public void testSaveValidDeals() {

        List<ValidDeal> validDeals = new ArrayList<>();

        dealService.saveValidDeals(validDeals);

        verify(validDealRepository, atLeastOnce()).batchSave(eq(validDeals));
    }

    @Test
    public void testSaveInvalidDeals() {

        List<InvalidDeal> invalidDeals = new ArrayList<>();

        dealService.saveInvalidDeals(invalidDeals);

        verify(invalidDealRepository, atLeastOnce()).batchSave(eq(invalidDeals));
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

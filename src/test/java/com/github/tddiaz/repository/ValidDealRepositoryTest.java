package com.github.tddiaz.repository;

import com.github.tddiaz.RepositoryTest;
import com.github.tddiaz.domain.CurrencyCode;
import com.github.tddiaz.domain.ValidDeal;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import static org.hamcrest.Matchers.hasProperty;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.notNullValue;
import static org.junit.Assert.assertThat;

/**
 * Created by tristandiaz on 10/31/17.
 */
public class ValidDealRepositoryTest extends RepositoryTest {

    @Autowired
    private ValidDealRepository validDealRepository;

    @Test
    public void testFindByDealId() {

        {
            ValidDeal validDeal = new ValidDeal();
            validDeal.setDateTime(LocalDateTime.now());
            validDeal.setAmount(new BigDecimal(0));
            validDeal.setFromCurrency(CurrencyCode.AED);
            validDeal.setToCurrency(CurrencyCode.AFN);
            validDeal.setFileName("filename.csv");
            validDeal.setDealId("0001");

            testEntityManager.persistAndFlush(validDeal);
        }

        ValidDeal foundDeal = validDealRepository.findByDealId("0001");

        assertThat(foundDeal, notNullValue());
        assertThat(foundDeal, hasProperty("dealId", is("0001")));

    }
}

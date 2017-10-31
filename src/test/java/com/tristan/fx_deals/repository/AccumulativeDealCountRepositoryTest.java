package com.tristan.fx_deals.repository;

import com.tristan.fx_deals.RepositoryTest;
import com.tristan.fx_deals.domain.AccumulativeDealCount;
import com.tristan.fx_deals.domain.CurrencyCode;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Map;

import static org.junit.Assert.assertFalse;

/**
 * Created by tristandiaz on 11/1/17.
 */
public class AccumulativeDealCountRepositoryTest extends RepositoryTest {

    @Autowired
    private AccumulativeDealCountRepository accumulativeDealCountRepository;

    @Before
    public void setup() {
        for (CurrencyCode currencyCode : CurrencyCode.values()) {
            testEntityManager.persistAndFlush(new AccumulativeDealCount(currencyCode));
        }
    }

    @Test
    public void testFindAllCurrencyCountMap() {
        Map<CurrencyCode, Long> currencyCountMap = accumulativeDealCountRepository.findAllCurrencyCountMap();
        assertFalse(currencyCountMap.isEmpty());
    }

}

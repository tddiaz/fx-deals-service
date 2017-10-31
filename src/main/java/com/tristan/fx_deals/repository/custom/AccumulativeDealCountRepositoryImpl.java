package com.tristan.fx_deals.repository.custom;

import com.tristan.fx_deals.domain.CurrencyCode;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by tristandiaz on 11/1/17.
 */
@Repository
public class AccumulativeDealCountRepositoryImpl implements AccumulativeDealCountRepositoryCustom {

    private EntityManager entityManager;

    @PersistenceContext
    public void setEntityManager(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public Map<CurrencyCode, Long> findAllCurrencyCountMap() {

        final TypedQuery<Object[]> typedQuery = entityManager.createQuery("SELECT adc.currencyCode, adc.countOfDeals FROM AccumulativeDealCount adc", Object[].class);

        final List<Object[]> result = typedQuery.getResultList();

        final Map<CurrencyCode, Long> map = new HashMap<>(result.size());

        for (Object[] obj : result) {
            map.put((CurrencyCode) obj[0], (Long) obj[1]);
        }

        return map;
    }
}

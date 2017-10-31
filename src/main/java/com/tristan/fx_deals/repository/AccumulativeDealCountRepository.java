package com.tristan.fx_deals.repository;

import com.tristan.fx_deals.domain.AccumulativeDealCount;
import com.tristan.fx_deals.domain.CurrencyCode;
import com.tristan.fx_deals.repository.custom.AccumulativeDealCountRepositoryCustom;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by tristandiaz on 10/29/17.
 */
@Repository
public interface AccumulativeDealCountRepository extends JpaRepository<AccumulativeDealCount, CurrencyCode>, AccumulativeDealCountRepositoryCustom {

}

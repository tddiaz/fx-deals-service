package com.github.tddiaz.repository;

import com.github.tddiaz.domain.AccumulativeDealCount;
import com.github.tddiaz.repository.custom.AccumulativeDealCountRepositoryCustom;
import com.github.tddiaz.domain.CurrencyCode;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by tristandiaz on 10/29/17.
 */
@Repository
public interface AccumulativeDealCountRepository extends JpaRepository<AccumulativeDealCount, CurrencyCode>, AccumulativeDealCountRepositoryCustom {

}

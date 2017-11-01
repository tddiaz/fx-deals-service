package com.github.tddiaz.repository;

import com.github.tddiaz.domain.ValidDeal;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.UUID;

/**
 * Created by tristandiaz on 10/29/17.
 */
@Repository
public interface ValidDealRepository extends JpaRepository<ValidDeal, UUID> {

    ValidDeal findByDealId(@Param("dealId") String dealId);
}
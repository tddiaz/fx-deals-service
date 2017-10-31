package com.tristan.fx_deals.repository;

import com.tristan.fx_deals.domain.InvalidDeal;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

/**
 * Created by tristandiaz on 10/29/17.
 */
@Repository
public interface InvalidDealRepository extends JpaRepository<InvalidDeal, UUID> {

}

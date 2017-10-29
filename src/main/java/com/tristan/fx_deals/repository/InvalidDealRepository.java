package com.tristan.fx_deals.repository;

import com.tristan.fx_deals.domain.InvalidDeal;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

/**
 * Created by tristandiaz on 10/29/17.
 */
@Repository
public interface InvalidDealRepository extends CrudRepository<InvalidDeal, UUID> {

}

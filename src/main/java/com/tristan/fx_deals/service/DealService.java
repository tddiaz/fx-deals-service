package com.tristan.fx_deals.service;

import com.tristan.fx_deals.domain.InvalidDeal;
import com.tristan.fx_deals.domain.ValidDeal;

import java.util.List;

/**
 * Created by tristandiaz on 10/29/17.
 */
public interface DealService {

    void batchSave(List<ValidDeal> validDeals, List<InvalidDeal> invalidDeals);

}

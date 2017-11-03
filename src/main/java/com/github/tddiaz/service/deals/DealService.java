package com.github.tddiaz.service.deals;


import com.github.tddiaz.domain.InvalidDeal;
import com.github.tddiaz.domain.ValidDeal;

import java.util.List;

/**
 * Created by tristandiaz on 10/29/17.
 */
public interface DealService {

    ValidDeal findValidDealByDealId(String dealId);

    void saveValidDeals(List<ValidDeal> validDeals);

    void saveInvalidDeals(List<InvalidDeal> invalidDeals);

}

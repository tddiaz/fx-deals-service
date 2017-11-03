package com.github.tddiaz.repository.custom;

import com.github.tddiaz.domain.ValidDeal;

import java.util.List;

/**
 * Created by tristandiaz on 11/3/17.
 */
public interface ValidDealRepositoryCustom {

    void batchSave(List<ValidDeal> validDeals);

}

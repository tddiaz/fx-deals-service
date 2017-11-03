package com.github.tddiaz.repository.custom;

import com.github.tddiaz.domain.InvalidDeal;

import java.util.List;

/**
 * Created by tristandiaz on 11/3/17.
 */
public interface InvalidDealRepositoryCustom {

    void batchSave(List<InvalidDeal> invalidDeals);
}

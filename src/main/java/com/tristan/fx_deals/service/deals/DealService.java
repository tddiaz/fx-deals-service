package com.tristan.fx_deals.service.deals;


import com.tristan.fx_deals.domain.ValidDeal;
import com.tristan.fx_deals.service.dto.DealDto;

import java.util.List;

/**
 * Created by tristandiaz on 10/29/17.
 */
public interface DealService {

    void batchSave(List<DealDto> dealDtos);

    ValidDeal findValidDealByDealId(String dealId);

}

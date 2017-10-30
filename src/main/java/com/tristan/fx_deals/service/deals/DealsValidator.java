package com.tristan.fx_deals.service.deals;

import com.tristan.fx_deals.service.dto.DealDto;

/**
 * Created by tristandiaz on 10/30/17.
 */
public interface DealsValidator {

    boolean valid(DealDto dealDto);

}

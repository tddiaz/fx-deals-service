package com.github.tddiaz.service.deals;

import com.github.tddiaz.service.dto.DealDto;

/**
 * Created by tristandiaz on 10/30/17.
 */
public interface DealsValidator {

    boolean valid(DealDto dealDto);

}

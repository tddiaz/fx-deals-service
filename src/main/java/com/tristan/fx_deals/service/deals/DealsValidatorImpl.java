package com.tristan.fx_deals.service.deals;

import com.tristan.fx_deals.service.dto.DealDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.validation.Validator;

/**
 * Created by tristandiaz on 10/30/17.
 */
@Component("dealsValidator")
public class DealsValidatorImpl implements DealsValidator {

    private Validator validator;

    @Autowired
    public void setValidator(Validator validator) {
        this.validator = validator;
    }

    @Override
    public boolean valid(DealDto dealDto) {
       return validator.validate(dealDto).size() <= 0;
    }
}

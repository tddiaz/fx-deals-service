package com.tristan.fx_deals.validation;

import com.tristan.fx_deals.domain.CurrencyCode;
import com.tristan.fx_deals.validation.constraints.ValidISOCurrency;
import org.apache.commons.lang3.StringUtils;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

/**
 * Created by tristandiaz on 10/30/17.
 */
public final class ISOCurrenyCodeValidator implements ConstraintValidator<ValidISOCurrency, String> {

    @Override
    public void initialize(ValidISOCurrency constraintAnnotation) {

    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {

        if (StringUtils.isBlank(value)) {
            return true;
        }

        return CurrencyCode.ISO_CURRENCY_CODES.contains(value);
    }
}

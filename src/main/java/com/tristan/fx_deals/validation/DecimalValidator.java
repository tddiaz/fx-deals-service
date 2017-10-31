package com.tristan.fx_deals.validation;

import com.tristan.fx_deals.validation.constraints.Decimal;
import org.apache.commons.lang3.StringUtils;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.math.BigDecimal;

/**
 * Created by tristandiaz on 10/31/17.
 */
public class DecimalValidator implements ConstraintValidator<Decimal, String> {

    @Override
    public void initialize(Decimal constraintAnnotation) {

    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {

        if (StringUtils.isBlank(value)) {
            return true;
        }

        try {
            new BigDecimal(value);
        } catch (NumberFormatException e) {
            return false;
        }

        return true;
    }
}

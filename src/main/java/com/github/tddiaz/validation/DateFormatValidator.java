package com.github.tddiaz.validation;

import com.github.tddiaz.validation.constraints.ValidDateFormat;
import org.apache.commons.lang3.StringUtils;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;

import static com.github.tddiaz.util.DateTimeUtil.*;

/**
 * Created by tristandiaz on 10/31/17.
 */
public class DateFormatValidator implements ConstraintValidator<ValidDateFormat, String> {

    @Override
    public void initialize(ValidDateFormat constraintAnnotation) {

    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {

        if (StringUtils.isBlank(value)) {
            return true;
        }

        try {
            LocalDateTime.parse(value, FORMATTER);
        } catch (DateTimeParseException e) {
            return false;
        }

        return true;
    }
}

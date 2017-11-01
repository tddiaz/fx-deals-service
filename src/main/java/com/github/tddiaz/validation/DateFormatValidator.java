package com.github.tddiaz.validation;

import com.github.tddiaz.validation.constraints.ValidDateFormat;
import org.apache.commons.lang3.StringUtils;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * Created by tristandiaz on 10/31/17.
 */
public class DateFormatValidator implements ConstraintValidator<ValidDateFormat, String> {

    private static final String DATE_TIME_PATTERN = "yyyy-MM-dd HH:mm:ss";

    @Override
    public void initialize(ValidDateFormat constraintAnnotation) {

    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {

        if (StringUtils.isBlank(value)) {
            return true;
        }

        final DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern(DATE_TIME_PATTERN);

        try {
            LocalDateTime.parse(value, dateTimeFormatter);
        } catch (DateTimeParseException e) {
            return false;
        }

        return true;
    }
}

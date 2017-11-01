package com.github.tddiaz.validation;

import com.github.tddiaz.validation.constraints.ValidDateFormat;
import lombok.Setter;
import org.junit.Test;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;

import java.util.Set;

import static org.hamcrest.Matchers.hasSize;
import static org.junit.Assert.assertThat;

/**
 * Created by tristandiaz on 10/31/17.
 */
public class DateFormatValidatorTest {

    private Validator validator = Validation.buildDefaultValidatorFactory().getValidator();

    @Test
    public void testInvalid() {
        {
            BeanValidation beanValidation = new BeanValidation();
            beanValidation.setValue("INVALID");

            Set<ConstraintViolation<BeanValidation>> constraintViolations = validator.validate(beanValidation);

            assertThat(constraintViolations, hasSize(1));
        }

        {
            BeanValidation beanValidation = new BeanValidation();
            beanValidation.setValue("2016-03-04");

            Set<ConstraintViolation<BeanValidation>> constraintViolations = validator.validate(beanValidation);

            assertThat(constraintViolations, hasSize(1));
        }

        {
            BeanValidation beanValidation = new BeanValidation();
            beanValidation.setValue("2016-03-04 12:00");

            Set<ConstraintViolation<BeanValidation>> constraintViolations = validator.validate(beanValidation);

            assertThat(constraintViolations, hasSize(1));
        }
    }

    @Test
    public void testValid() {

        BeanValidation beanValidation = new BeanValidation();
        beanValidation.setValue("2017-01-01 00:00:00");

        Set<ConstraintViolation<BeanValidation>> constraintViolations = validator.validate(beanValidation);

        assertThat(constraintViolations, hasSize(0));
    }

    @Setter
    private class BeanValidation {

        @ValidDateFormat
        String value;
    }
}

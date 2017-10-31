package com.tristan.fx_deals.validation;

import com.tristan.fx_deals.validation.constraints.Decimal;
import lombok.Setter;
import org.junit.Test;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import java.math.BigDecimal;
import java.util.Set;

import static org.hamcrest.Matchers.hasSize;
import static org.junit.Assert.assertThat;

/**
 * Created by tristandiaz on 10/31/17.
 */
public class DecimalValidatorTest {

    private Validator validator = Validation.buildDefaultValidatorFactory().getValidator();

    @Test
    public void testValid() {

        {
            BeanValidation beanValidation = new BeanValidation();
            beanValidation.setValue("1.00");

            Set<ConstraintViolation<BeanValidation>> constraintViolations = validator.validate(beanValidation);

            assertThat(constraintViolations, hasSize(0));
        }

        {
            BeanValidation beanValidation = new BeanValidation();
            beanValidation.setValue("0.0");

            Set<ConstraintViolation<BeanValidation>> constraintViolations = validator.validate(beanValidation);

            assertThat(constraintViolations, hasSize(0));
        }

        {
            BeanValidation beanValidation = new BeanValidation();
            beanValidation.setValue("100.0001");

            Set<ConstraintViolation<BeanValidation>> constraintViolations = validator.validate(beanValidation);

            assertThat(constraintViolations, hasSize(0));
        }
    }

    @Test
    public void testInvalid() {

        {
            BeanValidation beanValidation = new BeanValidation();
            beanValidation.setValue("$1.00");

            Set<ConstraintViolation<BeanValidation>> constraintViolations = validator.validate(beanValidation);

            assertThat(constraintViolations, hasSize(1));
        }

        {
            BeanValidation beanValidation = new BeanValidation();
            beanValidation.setValue("1,000,000.00");

            Set<ConstraintViolation<BeanValidation>> constraintViolations = validator.validate(beanValidation);

            assertThat(constraintViolations, hasSize(1));
        }
    }

    @Setter
    private class BeanValidation {

        @Decimal
        String value;
    }

}

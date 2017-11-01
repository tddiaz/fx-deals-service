package com.github.tddiaz.validation;

import com.github.tddiaz.validation.constraints.ValidISOCurrency;
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
public class ISOCurrencyCodeValidatorTest {

    private Validator validator = Validation.buildDefaultValidatorFactory().getValidator();

    @Test
    public void testInvalid() {

        BeanValidation beanValidation = new BeanValidation();
        beanValidation.setValue("INVALID");

        Set<ConstraintViolation<BeanValidation>> constraintViolations = validator.validate(beanValidation);

        assertThat(constraintViolations, hasSize(1));
    }

    @Test
    public void testValid() {

        {
            BeanValidation beanValidation = new BeanValidation();
            beanValidation.setValue("AED");

            Set<ConstraintViolation<BeanValidation>> constraintViolations = validator.validate(beanValidation);

            assertThat(constraintViolations, hasSize(0));
        }

        {
            BeanValidation beanValidation = new BeanValidation();
            beanValidation.setValue("PHP");

            Set<ConstraintViolation<BeanValidation>> constraintViolations = validator.validate(beanValidation);

            assertThat(constraintViolations, hasSize(0));
        }
    }


    @Setter
    private class BeanValidation {

        @ValidISOCurrency
        String value;
    }

}

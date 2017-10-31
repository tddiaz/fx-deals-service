package com.tristan.fx_deals.validation;

import com.tristan.fx_deals.SpringIntegrationTest;
import com.tristan.fx_deals.domain.CurrencyCode;
import com.tristan.fx_deals.domain.ValidDeal;
import com.tristan.fx_deals.repository.ValidDealRepository;
import com.tristan.fx_deals.validation.constraints.UniqueDealId;
import lombok.Setter;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Set;

import static org.hamcrest.Matchers.hasSize;
import static org.junit.Assert.assertThat;

/**
 * Created by tristandiaz on 10/31/17.
 */
public class DealIdUniqueValidatorTest extends SpringIntegrationTest {

    private Validator validator = Validation.buildDefaultValidatorFactory().getValidator();

    @Autowired
    private ValidDealRepository validDealRepository;

    @Test
    public void testUnique_true() {

        BeanValidation beanValidation = new BeanValidation();
        beanValidation.setValue("0000");

        Set<ConstraintViolation<BeanValidation>> constraintViolations = validator.validate(beanValidation);

        assertThat(constraintViolations, hasSize(0));
    }

    @Test
    public void testUnique_false() {

        {
            ValidDeal validDeal = new ValidDeal();
            validDeal.setDateTime(LocalDateTime.now());
            validDeal.setAmount(new BigDecimal(0));
            validDeal.setFromCurrency(CurrencyCode.AED);
            validDeal.setToCurrency(CurrencyCode.AFN);
            validDeal.setFileName("filename.csv");
            validDeal.setDealId("0001");

            validDealRepository.save(validDeal);
        }

        BeanValidation beanValidation = new BeanValidation();
        beanValidation.setValue("0001");

        Set<ConstraintViolation<BeanValidation>> constraintViolations = validator.validate(beanValidation);

        assertThat(constraintViolations, hasSize(1));
    }

    @Setter
    private class BeanValidation {

        @UniqueDealId
        String value;
    }
}

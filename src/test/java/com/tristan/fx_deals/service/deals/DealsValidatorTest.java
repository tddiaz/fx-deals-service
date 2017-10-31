package com.tristan.fx_deals.service.deals;

import com.tristan.fx_deals.service.dto.DealDto;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import javax.validation.ConstraintViolation;
import javax.validation.Path;
import javax.validation.Validator;
import javax.validation.metadata.ConstraintDescriptor;
import java.util.HashSet;
import java.util.Set;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.atLeastOnce;
import static org.mockito.Mockito.eq;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * Created by tristandiaz on 10/31/17.
 */
@RunWith(MockitoJUnitRunner.class)
public class DealsValidatorTest {

    @Mock
    private Validator validator;

    @InjectMocks
    private DealsValidator dealsValidator = new DealsValidatorImpl();

    @Test
    public void testIsValid_valid() {

        DealDto dealDto = new DealDto();

        when(validator.validate(eq(dealDto))).thenReturn(new HashSet<>());

        boolean isValid = dealsValidator.valid(dealDto);

        verify(validator, atLeastOnce()).validate(eq(dealDto));
        assertTrue(isValid);
    }


    @Test
    public void testIsValid_invalid() {

        DealDto dealDto = new DealDto();

        Set<ConstraintViolation<DealDto>> constraintViolationSet = new HashSet<>();
        constraintViolationSet.add(new ConstraintViolationImpl<>());

        when(validator.validate(eq(dealDto))).thenReturn(constraintViolationSet);

        boolean isValid = dealsValidator.valid(dealDto);

        verify(validator, atLeastOnce()).validate(eq(dealDto));
        assertFalse(isValid);

    }

    private class ConstraintViolationImpl<T> implements ConstraintViolation<T> {

        @Override
        public String getMessage() {

            return null;
        }

        @Override
        public String getMessageTemplate() {

            return null;
        }

        @Override
        public T getRootBean() {

            return null;
        }

        @Override
        public Class<T> getRootBeanClass() {

            return null;
        }

        @Override
        public Object getLeafBean() {

            return null;
        }

        @Override
        public Object[] getExecutableParameters() {

            return new Object[0];
        }

        @Override
        public Object getExecutableReturnValue() {

            return null;
        }

        @Override
        public Path getPropertyPath() {

            return null;
        }

        @Override
        public Object getInvalidValue() {

            return null;
        }

        @Override
        public ConstraintDescriptor<?> getConstraintDescriptor() {

            return null;
        }

        @Override
        public <U> U unwrap(Class<U> type) {

            return null;
        }
    }

}

package com.github.tddiaz.service.deals;

import com.github.tddiaz.service.dto.DealDto;
import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * Created by tristandiaz on 10/31/17.
 */
public class DealsValidatorTest {

    private DealsValidator dealsValidator = new DealsValidatorImpl();

    @Test
    public void testIsValid_invalid() {

        { // all values are null
            DealDto dealDto = new DealDto();

            assertFalse(dealsValidator.valid(dealDto));
        }

        { // amount is blank/empty/null
            DealDto dealDto = new DealDto();
            dealDto.setAmount("");
            dealDto.setDealId("1");
            dealDto.setDateTime("2017-11-03 01:57:59");
            dealDto.setFromCurrency("PHP");
            dealDto.setToCurrency("AED");

            assertFalse(dealsValidator.valid(dealDto));
        }

        { // amount invalid decimal format
            DealDto dealDto = new DealDto();
            dealDto.setAmount("$1000.00");
            dealDto.setDealId("1");
            dealDto.setDateTime("2017-11-03 01:57:59");
            dealDto.setFromCurrency("PHP");
            dealDto.setToCurrency("AED");

            assertFalse(dealsValidator.valid(dealDto));
        }

        { // deal id is blank/empty/null
            DealDto dealDto = new DealDto();
            dealDto.setAmount("1000.00");
            dealDto.setDealId("");
            dealDto.setDateTime("2017-11-03 01:57:59");
            dealDto.setFromCurrency("PHP");
            dealDto.setToCurrency("AED");

            assertFalse(dealsValidator.valid(dealDto));
        }

        { // date time is blank/empty/null
            DealDto dealDto = new DealDto();
            dealDto.setAmount("1000.00");
            dealDto.setDealId("11");
            dealDto.setDateTime("");
            dealDto.setFromCurrency("PHP");
            dealDto.setToCurrency("AED");

            assertFalse(dealsValidator.valid(dealDto));
        }

        { // date time invalid format
            DealDto dealDto = new DealDto();
            dealDto.setAmount("1000.00");
            dealDto.setDealId("11");
            dealDto.setDateTime("2017/11/03 01:57:59");
            dealDto.setFromCurrency("PHP");
            dealDto.setToCurrency("AED");

            assertFalse(dealsValidator.valid(dealDto));
        }

        { // from currency is empty/blank/null
            DealDto dealDto = new DealDto();
            dealDto.setAmount("1000.00");
            dealDto.setDealId("11");
            dealDto.setDateTime("2017-11-03 01:57:59");
            dealDto.setFromCurrency("");
            dealDto.setToCurrency("AED");

            assertFalse(dealsValidator.valid(dealDto));
        }

        { // from currency code invalid
            DealDto dealDto = new DealDto();
            dealDto.setAmount("1000.00");
            dealDto.setDealId("11");
            dealDto.setDateTime("2017-11-03 01:57:59");
            dealDto.setFromCurrency("*#&#");
            dealDto.setToCurrency("AED");

            assertFalse(dealsValidator.valid(dealDto));
        }

        { // to currency is empty/blank/null
            DealDto dealDto = new DealDto();
            dealDto.setAmount("1000.00");
            dealDto.setDealId("11");
            dealDto.setDateTime("2017-11-03 01:57:59");
            dealDto.setFromCurrency("AED");
            dealDto.setToCurrency("");

            assertFalse(dealsValidator.valid(dealDto));
        }

        { // from currency code invalid
            DealDto dealDto = new DealDto();
            dealDto.setAmount("1000.00");
            dealDto.setDealId("11");
            dealDto.setDateTime("2017-11-03 01:57:59");
            dealDto.setFromCurrency("USD");
            dealDto.setToCurrency("*$($");

            assertFalse(dealsValidator.valid(dealDto));
        }


    }


    @Test
    public void testIsValid_valid() {

        DealDto dealDto = new DealDto();
        dealDto.setAmount("1000.00");
        dealDto.setDealId("0101011");
        dealDto.setDateTime("2017-11-03 01:57:59");
        dealDto.setFromCurrency("PHP");
        dealDto.setToCurrency("AED");

        assertTrue(dealsValidator.valid(dealDto));
    }


}

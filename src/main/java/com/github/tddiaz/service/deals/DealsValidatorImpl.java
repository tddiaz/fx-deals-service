package com.github.tddiaz.service.deals;

import com.github.tddiaz.domain.CurrencyCode;
import com.github.tddiaz.service.dto.DealDto;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;

import static com.github.tddiaz.util.DateTimeUtil.FORMATTER;

/**
 * Created by tristandiaz on 10/30/17.
 */
@Component("dealsValidator")
public class DealsValidatorImpl implements DealsValidator {

    @Override
    public boolean valid(DealDto dealDto) {

        if (StringUtils.isEmpty(dealDto.getDealId())) {
            return false;
        }

        if (StringUtils.isEmpty(dealDto.getToCurrency())) {
            return false;
        } else {
            if (!CurrencyCode.ISO_CURRENCY_CODES.contains(dealDto.getToCurrency())) {
                return false;
            }
        }

        if (StringUtils.isEmpty(dealDto.getFromCurrency())) {
            return false;
        } else {
           if (!CurrencyCode.ISO_CURRENCY_CODES.contains(dealDto.getFromCurrency())) {
               return false;
           }
        }

        if (StringUtils.isEmpty(dealDto.getDateTime())) {
            return false;
        } else {
            try {
                LocalDateTime.parse(dealDto.getDateTime(), FORMATTER);
            } catch (DateTimeParseException e) {
                return false;
            }
        }

        if (StringUtils.isEmpty(dealDto.getAmount())) {
            return false;
        } else {
            try {
                new BigDecimal(dealDto.getAmount());
            } catch (NumberFormatException e) {
                return false;
            }
        }

        return true;
    }
}

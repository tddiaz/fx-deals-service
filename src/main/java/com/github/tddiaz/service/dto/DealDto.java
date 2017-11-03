package com.github.tddiaz.service.dto;

import com.github.tddiaz.validation.constraints.Decimal;
import com.github.tddiaz.validation.constraints.ValidDateFormat;
import com.github.tddiaz.validation.constraints.ValidISOCurrency;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.NotBlank;

/**
 * Created by tristandiaz on 10/30/17.
 */
@Getter
@Setter
public class DealDto {

    @NotBlank
//    @UniqueDealId
    private String dealId;

    @NotBlank
    @ValidISOCurrency
    private String fromCurrency;

    @NotBlank
    @ValidISOCurrency
    private String toCurrency;

    @NotBlank
    @ValidDateFormat
    private String dateTime;

    @NotBlank
    @Decimal
    private String amount;

    public DealDto() {

    }
}

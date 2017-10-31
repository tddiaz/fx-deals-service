package com.tristan.fx_deals.service.dto;

import com.tristan.fx_deals.domain.ValidDeal;
import com.tristan.fx_deals.validation.constraints.Decimal;
import com.tristan.fx_deals.validation.constraints.UniqueDealId;
import com.tristan.fx_deals.validation.constraints.ValidDateFormat;
import com.tristan.fx_deals.validation.constraints.ValidISOCurrency;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;

/**
 * Created by tristandiaz on 10/30/17.
 */
@Getter
@Setter
public class DealDto {

    @NotNull
    @UniqueDealId
    private String dealId;

    @NotNull
    @ValidISOCurrency
    private String fromCurrency;

    @NotNull
    @ValidISOCurrency
    private String toCurrency;

    @NotNull
    @ValidDateFormat
    private String dateTime;

    @NotNull
    @Decimal
    private String amount;

    @NotNull
    private String fileName;

    public DealDto() {

    }
}

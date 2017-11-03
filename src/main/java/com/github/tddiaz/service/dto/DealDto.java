package com.github.tddiaz.service.dto;

import lombok.Getter;
import lombok.Setter;

/**
 * Created by tristandiaz on 10/30/17.
 */
@Getter
@Setter
public class DealDto {

    private String dealId;

    private String fromCurrency;

    private String toCurrency;

    private String dateTime;

    private String amount;

    public DealDto() {

    }
}

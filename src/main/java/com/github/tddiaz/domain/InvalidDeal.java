package com.github.tddiaz.domain;

import com.github.tddiaz.service.dto.DealDto;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * Created by tristandiaz on 10/29/17.
 */
@Entity
@Table(name = "invalid_deal")
@Getter
@Setter
public class InvalidDeal extends Deal {

    @Column(name = "deal_id", nullable = true)
    private String dealId;

    @Column(name = "from_currency", nullable = true)
    private String fromCurrency;

    @Column(name = "to_currency", nullable = true)
    private String toCurrency;

    @Column(name = "date_time", nullable = true)
    private String dateTime;

    @Column(name = "amount", nullable = true)
    private String amount;

    public InvalidDeal() {

    }

    public static InvalidDeal valueOf(DealDto dealDto) {

        final InvalidDeal invalidDeal = new InvalidDeal();
        invalidDeal.setDealId(dealDto.getDealId());
        invalidDeal.setFromCurrency(dealDto.getFromCurrency());
        invalidDeal.setToCurrency(dealDto.getToCurrency());
        invalidDeal.setAmount(dealDto.getAmount());

        return invalidDeal;
    }
}

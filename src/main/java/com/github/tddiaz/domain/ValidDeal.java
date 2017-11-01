package com.github.tddiaz.domain;

import com.github.tddiaz.service.dto.DealDto;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Table;
import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * Created by tristandiaz on 10/29/17.
 */
@Entity
@Table(name = "valid_deal")
@Getter
@Setter
public class ValidDeal extends Deal {

    @Column(name = "deal_id", nullable = false, unique = true)
    private String dealId;

    @Column(name = "from_currency")
    @Enumerated(EnumType.STRING)
    private CurrencyCode fromCurrency;

    @Column(name = "to_currency")
    @Enumerated(EnumType.STRING)
    private CurrencyCode toCurrency;

    @Column(name = "date_time")
    private LocalDateTime dateTime;

    @Column(name = "amount")
    private BigDecimal amount;

    public ValidDeal() {

    }

    public static ValidDeal valueOf(DealDto dealDto) {

        final ValidDeal validDeal = new ValidDeal();
        validDeal.setDealId(dealDto.getDealId());
        validDeal.setFromCurrency(CurrencyCode.valueOf(dealDto.getFromCurrency()));
        validDeal.setToCurrency(CurrencyCode.valueOf(dealDto.getToCurrency()));
        validDeal.setAmount(new BigDecimal(dealDto.getAmount()));

        return  validDeal;
    }
}

package com.github.tddiaz.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Created by tristandiaz on 10/29/17.
 */
@Entity
@Table(name = "accumulative_deal_count")
@Getter
@Setter
public class AccumulativeDealCount {

    @Id
    @Column(name = "curreny_code", unique = true, nullable = false)
    @Enumerated(EnumType.STRING)
    private CurrencyCode currencyCode;

    @Column(name = "count_of_deals")
    private long countOfDeals = 0;

    public AccumulativeDealCount() {

    }

    public AccumulativeDealCount(CurrencyCode currencyCode) {
        this.currencyCode = currencyCode;
    }
}

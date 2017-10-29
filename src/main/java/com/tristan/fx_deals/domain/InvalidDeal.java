package com.tristan.fx_deals.domain;

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

    @Column(name = "data")
    private String data;

    public InvalidDeal() {

    }
}

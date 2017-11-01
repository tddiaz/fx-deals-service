package com.github.tddiaz.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;

/**
 * Created by tristandiaz on 10/29/17.
 */
@MappedSuperclass
@Getter
@Setter
public class Deal extends AbstractDomain {

    @Column(name = "file_name")
    private String fileName;

    public Deal() {

    }

}

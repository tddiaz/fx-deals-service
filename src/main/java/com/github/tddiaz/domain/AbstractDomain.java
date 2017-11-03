package com.github.tddiaz.domain;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import java.util.UUID;

/**
 * Created by tristandiaz on 10/29/17.
 */
@MappedSuperclass
@Getter
@Setter
public abstract class AbstractDomain {

    @Id
    @Column(name = "id", nullable = false, unique = true)
    @Setter(AccessLevel.NONE)
    private String id;

    public AbstractDomain() {
        this.id = UUID.randomUUID().toString();
    }

}

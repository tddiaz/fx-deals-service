package com.github.tddiaz.domain;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
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
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
    @Column(name = "id", nullable = false, unique = true)
    @Setter(AccessLevel.NONE)
    private UUID id;

    public AbstractDomain() {

    }

}

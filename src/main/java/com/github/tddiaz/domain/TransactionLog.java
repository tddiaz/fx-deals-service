package com.github.tddiaz.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.time.Clock;
import java.time.LocalDateTime;

/**
 * Created by tristandiaz on 10/29/17.
 */
@Entity
@Table(name = "transaction_log")
@Getter
@Setter
public class TransactionLog extends AbstractDomain {

    @Column(name = "date_time", nullable = false)
    private LocalDateTime dateTime;

    @Column(name = "file_name", nullable = false)
    private String fileName;

    @Column(name = "deals_imported_count")
    private int dealsImportedCount;

    @Column(name = "invalid_deals_imported_count")
    private int invalidDealsImportedCount;

    @Column(name = "process_duration")
    private double processDuration;

    public TransactionLog() {
        this.dateTime = LocalDateTime.now(Clock.systemUTC());
    }
}

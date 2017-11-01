package com.github.tddiaz.repository;

import com.github.tddiaz.domain.TransactionLog;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

/**
 * Created by tristandiaz on 10/29/17.
 */
public interface TransactionLogRepository extends JpaRepository<TransactionLog, UUID> {

}

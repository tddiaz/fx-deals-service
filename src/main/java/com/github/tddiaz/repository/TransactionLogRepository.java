package com.github.tddiaz.repository;

import com.github.tddiaz.domain.TransactionLog;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by tristandiaz on 10/29/17.
 */
public interface TransactionLogRepository extends JpaRepository<TransactionLog, String> {

    TransactionLog findByFileName(String fileName);

}

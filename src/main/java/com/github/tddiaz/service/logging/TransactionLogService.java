package com.github.tddiaz.service.logging;

import com.github.tddiaz.domain.TransactionLog;

/**
 * Created by tristandiaz on 10/29/17.
 */
public interface TransactionLogService {

    TransactionLog save(TransactionLog transactionLog);

}

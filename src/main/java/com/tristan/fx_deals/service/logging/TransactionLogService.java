package com.tristan.fx_deals.service.logging;

import com.tristan.fx_deals.domain.TransactionLog;

/**
 * Created by tristandiaz on 10/29/17.
 */
public interface TransactionLogService {

    TransactionLog save(TransactionLog transactionLog);

}

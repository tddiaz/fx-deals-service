package com.tristan.fx_deals.service.logging;

import com.tristan.fx_deals.domain.TransactionLog;
import com.tristan.fx_deals.repository.TransactionLogRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by tristandiaz on 10/29/17.
 */
@Service("transactionLogService")
public class TransactionLogServiceImpl implements TransactionLogService {

    private TransactionLogRepository transactionLogRepository;

    @Autowired
    public void setTransactionLogRepository(TransactionLogRepository transactionLogRepository) {
        this.transactionLogRepository = transactionLogRepository;
    }

    @Override
    @Transactional
    public TransactionLog save(TransactionLog transactionLog) {
        return transactionLogRepository.saveAndFlush(transactionLog);
    }
}

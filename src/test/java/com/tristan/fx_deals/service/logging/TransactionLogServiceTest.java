package com.tristan.fx_deals.service.logging;

import com.tristan.fx_deals.domain.TransactionLog;
import com.tristan.fx_deals.repository.TransactionLogRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import static org.mockito.Mockito.*;
import static org.junit.Assert.*;
import static org.hamcrest.Matchers.is;

/**
 * Created by tristandiaz on 11/1/17.
 */
@RunWith(MockitoJUnitRunner.class)
public class TransactionLogServiceTest {

    @Mock
    private TransactionLogRepository transactionLogRepository;

    @InjectMocks
    private TransactionLogService transactionLogService = new TransactionLogServiceImpl();

    @Test
    public void testSave() {

        TransactionLog transactionLog = new TransactionLog();

        when(transactionLogRepository.saveAndFlush(any(TransactionLog.class))).thenReturn(transactionLog);

        TransactionLog log = transactionLogService.save(transactionLog);

        verify(transactionLogRepository, atLeastOnce()).saveAndFlush(eq(transactionLog));
        assertThat(log, is(transactionLog));
    }
}

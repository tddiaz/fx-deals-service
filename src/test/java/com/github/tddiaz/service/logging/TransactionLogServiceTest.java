package com.github.tddiaz.service.logging;

import com.github.tddiaz.domain.TransactionLog;
import com.github.tddiaz.repository.TransactionLogRepository;
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

        TransactionLog persistedEntity = transactionLogService.save(transactionLog);

        verify(transactionLogRepository, atLeastOnce()).saveAndFlush(eq(transactionLog));
        assertThat(persistedEntity, is(transactionLog));
    }

    @Test
    public void testFindByFileName() {

        String fileName = "filename.csv";

        TransactionLog transactionLog = new TransactionLog();
        transactionLog.setFileName(fileName);

        when(transactionLogRepository.findByFileName(eq(fileName))).thenReturn(transactionLog);

        TransactionLog foundEntity = transactionLogService.findByFileName(fileName);

        verify(transactionLogRepository, atLeastOnce()).findByFileName(eq(fileName));
        assertThat(foundEntity, is(transactionLog));
    }
}

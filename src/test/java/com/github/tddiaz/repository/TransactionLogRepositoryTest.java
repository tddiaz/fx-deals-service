package com.github.tddiaz.repository;

import com.github.tddiaz.RepositoryTest;
import com.github.tddiaz.domain.TransactionLog;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDateTime;

import static org.junit.Assert.*;
import static org.hamcrest.Matchers.*;

/**
 * Created by tristandiaz on 11/1/17.
 */
public class TransactionLogRepositoryTest extends RepositoryTest {

    @Autowired
    private TransactionLogRepository transactionLogRepository;

    @Test
    public void testFindByFileName() {

        TransactionLog transactionLog = new TransactionLog();
        transactionLog.setDateTime(LocalDateTime.now());
        transactionLog.setFileName("filename.csv");

        testEntityManager.persistAndFlush(transactionLog);

        TransactionLog foundEntity = transactionLogRepository.findByFileName("filename.csv");
        assertThat(foundEntity, notNullValue());
        assertThat(foundEntity, hasProperty("fileName", is("filename.csv")));

    }
}

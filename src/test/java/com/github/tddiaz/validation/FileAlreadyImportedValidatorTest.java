package com.github.tddiaz.validation;

import com.github.tddiaz.SpringIntegrationTest;
import com.github.tddiaz.domain.TransactionLog;
import com.github.tddiaz.repository.TransactionLogRepository;
import com.github.tddiaz.validation.constraints.FileExists;
import com.github.tddiaz.validation.data.TestMultipartFile;
import lombok.Setter;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import java.time.LocalDateTime;
import java.util.Set;

import static org.hamcrest.Matchers.hasSize;
import static org.junit.Assert.assertThat;

/**
 * Created by tristandiaz on 11/1/17.
 */
public class FileAlreadyImportedValidatorTest extends SpringIntegrationTest {

    private Validator validator = Validation.buildDefaultValidatorFactory().getValidator();

    @Autowired
    private TransactionLogRepository transactionLogRepository;

    @Test
    public void testAlreadyImported_false() {

        BeanValidation beanValidation = new BeanValidation();
        beanValidation.setValue(new TestMultipartFile("filename.csv", 1) {
        });

        Set<ConstraintViolation<BeanValidation>> constraintViolations = validator.validate(beanValidation);

        assertThat(constraintViolations, hasSize(0));
    }

    @Test
    public void testAlreadyImported_true() {

        TransactionLog transactionLog = new TransactionLog();
        transactionLog.setDateTime(LocalDateTime.now());
        transactionLog.setFileName("test.csv");

        transactionLogRepository.saveAndFlush(transactionLog);

        BeanValidation beanValidation = new BeanValidation();
        beanValidation.setValue(new TestMultipartFile("test.csv", 1) {
        });

        Set<ConstraintViolation<BeanValidation>> constraintViolations = validator.validate(beanValidation);

        assertThat(constraintViolations, hasSize(1));

    }

    @Setter
    private class BeanValidation {

        @FileExists
        MultipartFile value;
    }

}

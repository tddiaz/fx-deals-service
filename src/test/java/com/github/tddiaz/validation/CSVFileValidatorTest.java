package com.github.tddiaz.validation;

import com.github.tddiaz.validation.constraints.CSV;
import com.github.tddiaz.validation.data.TestMultipartFile;
import lombok.Setter;
import org.junit.Test;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import java.util.Set;

import static org.hamcrest.Matchers.hasSize;
import static org.junit.Assert.assertThat;

/**
 * Created by tristandiaz on 11/2/17.
 */
public class CSVFileValidatorTest {

    private Validator validator = Validation.buildDefaultValidatorFactory().getValidator();

    @Test
    public void testInvalidFormat() {

        BeanValidation beanValidation = new BeanValidation();
        beanValidation.setFile(new TestMultipartFile("filename.csv", 1, "image/jpeg"));

        Set<ConstraintViolation<BeanValidation>> constraintViolations = validator.validate(beanValidation);

        assertThat(constraintViolations, hasSize(1));
    }

    @Test
    public void testValidFormat() {

        BeanValidation beanValidation = new BeanValidation();
        beanValidation.setFile(new TestMultipartFile("filename.csv", 1, "text/csv"));

        Set<ConstraintViolation<BeanValidation>> constraintViolations = validator.validate(beanValidation);

        assertThat(constraintViolations, hasSize(0));
    }

    @Setter
    private class BeanValidation {

        @CSV
        MultipartFile file;
    }
}

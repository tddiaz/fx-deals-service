package com.github.tddiaz.validation;

import com.github.tddiaz.validation.constraints.CSV;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

/**
 * Created by tristandiaz on 11/1/17.
 */
public class CSVFileValidator implements ConstraintValidator<CSV, MultipartFile> {

    private static final String CSV_CONTENT_TYPE = "text/csv";

    @Override
    public void initialize(CSV constraintAnnotation) {

    }

    @Override
    public boolean isValid(MultipartFile file, ConstraintValidatorContext context) {

        if (file.isEmpty()) {
            return true;
        }

        return file.getContentType().equals(CSV_CONTENT_TYPE);
    }
}

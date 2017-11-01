package com.github.tddiaz.validation;

import com.github.tddiaz.validation.constraints.FileNotEmpty;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

/**
 * Created by tristandiaz on 11/2/17.
 */
public class FileNotEmptyValidator implements ConstraintValidator<FileNotEmpty, MultipartFile> {

    @Override
    public void initialize(FileNotEmpty constraintAnnotation) {

    }

    @Override
    public boolean isValid(MultipartFile file, ConstraintValidatorContext context) {
        return !file.isEmpty();
    }
}

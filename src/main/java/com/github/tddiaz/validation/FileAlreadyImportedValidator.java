package com.github.tddiaz.validation;

import com.github.tddiaz.AppContextProvider;
import com.github.tddiaz.service.logging.TransactionLogService;
import com.github.tddiaz.validation.constraints.FileExists;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.Objects;

/**
 * Created by tristandiaz on 11/1/17.
 */
public class FileAlreadyImportedValidator implements ConstraintValidator<FileExists, MultipartFile> {

    private TransactionLogService transactionLogService;

    public FileAlreadyImportedValidator() {
        this.transactionLogService = AppContextProvider.getContext().getBean("transactionLogService", TransactionLogService.class);
    }

    @Override
    public void initialize(FileExists constraintAnnotation) {

    }

    @Override
    public boolean isValid(MultipartFile value, ConstraintValidatorContext context) {

        if (value.isEmpty()) {
            return true;
        }

        return Objects.isNull(transactionLogService.findByFileName(value.getOriginalFilename()));
    }
}

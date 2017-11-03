package com.github.tddiaz.service.csv;

import com.github.tddiaz.domain.TransactionLog;
import org.springframework.web.multipart.MultipartFile;

/**
 * Created by tristandiaz on 11/3/17.
 */
public interface FileUploadService {

    void uploadFile(MultipartFile file, TransactionLog transactionLog);
}

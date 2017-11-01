package com.github.tddiaz.controller;

import com.github.tddiaz.controller.dto.FileUploadForm;
import com.github.tddiaz.domain.TransactionLog;
import com.github.tddiaz.service.logging.TransactionLogService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.MDC;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;

/**
 * Created by tristandiaz on 10/29/17.
 */
@Controller
@RequestMapping("/upload")
public class FileUploadController {

    private static final Logger LOGGER = LoggerFactory.getLogger(FileUploadController.class);

    private TransactionLogService transactionLogService;

    @Autowired
    public void setTransactionLogService(TransactionLogService transactionLogService) {
        this.transactionLogService = transactionLogService;
    }

    @GetMapping
    public String getUploadPage(Model model) {

        model.addAttribute("fileUploadForm", new FileUploadForm());

        return "views/upload-file";
    }

    @PostMapping
    public String uploadFile(@Valid FileUploadForm form, BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {
            return "views/upload-file";
        }

        final MultipartFile file = form.getFile();

        final TransactionLog transactionLog = transactionLogService.save(new TransactionLog(file.getOriginalFilename()));

        MDC.put("logId", transactionLog.getId().toString());

        LOGGER.info("Upload File Request. File Name: {}", transactionLog.getFileName());

        return "views/upload-file";
    }


}

package com.github.tddiaz.controller;

import com.github.tddiaz.controller.dto.FileUploadForm;
import com.github.tddiaz.domain.TransactionLog;
import com.github.tddiaz.service.csv.FileUploadService;
import com.github.tddiaz.service.logging.TransactionLogService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.MDC;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;

/**
 * Created by tristandiaz on 10/29/17.
 */
@Controller
@RequestMapping("/upload")
public class FileUploadController {

    private static final Logger LOGGER = LoggerFactory.getLogger(FileUploadController.class);

    private TransactionLogService transactionLogService;

    private FileUploadService fileUploadService;

    @Autowired
    public void setFileUploadService(FileUploadService fileUploadService) {
        this.fileUploadService = fileUploadService;
    }

    @Autowired
    public void setTransactionLogService(TransactionLogService transactionLogService) {
        this.transactionLogService = transactionLogService;
    }

    @GetMapping
    public ModelAndView getUploadPage() {
        return getModelView();
    }

    @PostMapping
    public ModelAndView uploadFile(@ModelAttribute("fileUploadForm") @Valid FileUploadForm form, BindingResult bindingResult, Model model) {

        if (bindingResult.hasErrors()) {
            return new ModelAndView("views/upload-file");
        }

        final MultipartFile file = form.getFile();

        final TransactionLog transactionLog = transactionLogService.save(new TransactionLog(file.getOriginalFilename()));
        MDC.put("logId", transactionLog.getId().toString());

        fileUploadService.uploadFile(file, transactionLog);

        final ModelAndView modelAndView = getModelView();
        modelAndView.addObject("success", true);

        return modelAndView;
    }

    @ExceptionHandler(Exception.class)
    public ModelAndView handleException(Exception e) {
        LOGGER.error("Failed importing file.", e);

        final ModelAndView modelAndView = getModelView();
        modelAndView.addObject("error", true);

        return modelAndView;
    }

    private ModelAndView getModelView() {
        final ModelAndView modelAndView = new ModelAndView("views/upload-file");
        modelAndView.addObject("fileUploadForm", new FileUploadForm());

        return modelAndView;
    }

}

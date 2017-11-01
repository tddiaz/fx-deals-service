package com.github.tddiaz.controller;

import com.github.tddiaz.controller.dto.FileUploadForm;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by tristandiaz on 10/29/17.
 */
@Controller
@RequestMapping("/upload")
public class FileUploadController {


    @RequestMapping
    public String getUploadPage(Model model) {

        model.addAttribute("fileUploadForm", new FileUploadForm());

        return "";
    }

}

package com.github.tddiaz.controller.dto;

import com.github.tddiaz.validation.constraints.CSV;
import com.github.tddiaz.validation.constraints.FileExists;
import com.github.tddiaz.validation.constraints.FileNotEmpty;
import lombok.Getter;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.NotNull;

/**
 * Created by tristandiaz on 11/1/17.
 */
@Getter
@Setter
public class FileUploadForm {

    @FileNotEmpty
    @CSV
    @FileExists
    private MultipartFile file;

    public FileUploadForm() {
    }

}

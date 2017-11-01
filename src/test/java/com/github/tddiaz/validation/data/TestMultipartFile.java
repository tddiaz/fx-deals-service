package com.github.tddiaz.validation.data;

import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;

/**
 * Created by tristandiaz on 11/2/17.
 */
@Setter
public class TestMultipartFile implements MultipartFile {

    private String fileName;

    private long size = 0;

    private String contentType;

    public TestMultipartFile(String fileName) {
        this.fileName = fileName;
    }

    public TestMultipartFile(String fileName, long size) {
        this.fileName = fileName;
        this.size = size;
    }

    public TestMultipartFile(String fileName, long size, String contentType) {
        this.fileName = fileName;
        this.size = size;
        this.contentType = contentType;
    }

    @Override
    public String getName() {
        return null;
    }

    @Override
    public String getOriginalFilename() {
        return fileName;
    }

    @Override
    public String getContentType() {
        return contentType;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public long getSize() {
        return 0;
    }

    @Override
    public byte[] getBytes() throws IOException {
        return new byte[0];
    }

    @Override
    public InputStream getInputStream() throws IOException {
        return null;
    }

    @Override
    public void transferTo(File dest) throws IOException, IllegalStateException {

    }
}

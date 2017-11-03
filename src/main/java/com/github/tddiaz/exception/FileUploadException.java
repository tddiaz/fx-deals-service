package com.github.tddiaz.exception;

/**
 * Created by tristandiaz on 11/2/17.
 */
public class FileUploadException extends RuntimeException {

    private static final long serialVersionUID = -6134189005125488448L;

    public FileUploadException(String message, Throwable cause) {
        super(message, cause);
    }
}

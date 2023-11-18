package com.amigoscode.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.io.Serial;

@ResponseStatus(code = HttpStatus.NOT_FOUND)
public class CustomerNotFound extends RuntimeException {
    @Serial
    private static final long serialVersionUID = 1L;
    private String message;


    public CustomerNotFound(String message) {
        super(message);
        this.message = message;
    }
}

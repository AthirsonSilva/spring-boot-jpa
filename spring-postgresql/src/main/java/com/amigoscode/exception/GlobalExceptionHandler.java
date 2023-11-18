package com.amigoscode.exception;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import java.time.LocalDateTime;

import static org.springframework.http.HttpStatus.NOT_FOUND;

@ControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(CustomerNotFound.class)
    public ResponseEntity<ErrorDetails> handleCustomerNotFound(CustomerNotFound exception, WebRequest request) {
        ErrorDetails errorDetails = new ErrorDetails(
                exception.getMessage(),
                request.getDescription(false),
                LocalDateTime.now().toString(),
                String.valueOf(NOT_FOUND.value())
        );

        return ResponseEntity.status(NOT_FOUND).body(errorDetails);
    }
}

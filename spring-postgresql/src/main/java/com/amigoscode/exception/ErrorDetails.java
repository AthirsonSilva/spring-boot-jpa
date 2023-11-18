package com.amigoscode.exception;

public record ErrorDetails(
        String message,
        String path,
        String timestamp,
        String code
) {
}

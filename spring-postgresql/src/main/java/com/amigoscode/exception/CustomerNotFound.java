package com.amigoscode.exception;

import java.io.Serial;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.NOT_FOUND)
public class CustomerNotFound extends RuntimeException {

	@Serial
	private static final long serialVersionUID = 1L;

	public CustomerNotFound(String message) {
		super(message);
	}
}

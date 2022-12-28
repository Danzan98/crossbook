package ru.example.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public class WebApplicationException extends RuntimeException {
    private final HttpStatus httpCode;

    public WebApplicationException(String message, HttpStatus httpCode) {
        super(message);
        this.httpCode = httpCode;
    }
}

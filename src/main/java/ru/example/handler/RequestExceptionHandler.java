package ru.example.handler;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
import ru.example.exception.WebApplicationException;

import java.time.OffsetDateTime;

@Slf4j
@ControllerAdvice
public class RequestExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(WebApplicationException.class)
    public Object processWebAppException(WebApplicationException ex, WebRequest request) {
        log.warn("Request failed with message: {}", ex.getMessage());
        return handleExceptionInternal(
                ex,
                new ErrorResponse(ex.getMessage()),
                new HttpHeaders(),
                ex.getHttpCode(),
                request
        );
    }
    @Getter
    @Setter
    @RequiredArgsConstructor
    static class ErrorResponse {
        private final OffsetDateTime offsetDateTime = OffsetDateTime.now();
        private final String message;
    }
}

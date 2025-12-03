package com.glassgo.platform.shared.interfaces.rest;

import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.web.ErrorResponse;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * Global exception handler for REST controllers.
 * <p>
 * Centralises mapping of common exceptions to HTTP responses. Keep handlers
 * small and avoid exposing internal exception details in production; prefer
 * returning user-friendly messages or a `MessageResource` where appropriate.
 */
@RestControllerAdvice
public class GlobalExceptionHandler {
    /**
     * Handle validation errors thrown by Spring when request bodies fail
     * validation constraints.
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    ErrorResponse handleException(MethodArgumentNotValidException ex) {
        String message = ex.getFieldErrors().stream().map(fieldError -> fieldError.getDefaultMessage() == null ? "" : fieldError.getDefaultMessage()).reduce("", String::concat);
        return ErrorResponse.create(
                ex,
                HttpStatusCode.valueOf(HttpStatus.BAD_REQUEST.value()),
                message
        );
    }

    /**
     * Handle illegal argument exceptions commonly raised during compact
     * constructor validation or explicit guard checks.
     */
    @ExceptionHandler(IllegalArgumentException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    ErrorResponse handleException(IllegalArgumentException ex) {
        return ErrorResponse.create(ex, HttpStatusCode.valueOf(HttpStatus.BAD_REQUEST.value()), ex.getMessage());
    }
}

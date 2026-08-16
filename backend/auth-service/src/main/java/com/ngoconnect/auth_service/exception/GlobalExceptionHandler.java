package com.ngoconnect.auth_service.exception;

import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(EmailAlreadyExistsException.class)
    public ResponseEntity<Map<String, String>> handleEmailExists(
            EmailAlreadyExistsException ex) {

        return ResponseEntity
                .status(HttpStatus.CONFLICT)
                .body(Map.of(
                        "error", "EMAIL_ALREADY_EXISTS",
                        "message", ex.getMessage()
                ));
    }
    @ExceptionHandler(
        org.springframework.web.bind.MethodArgumentNotValidException.class
)
public ResponseEntity<Map<String, String>> handleValidation(
        org.springframework.web.bind.MethodArgumentNotValidException ex) {

    String message = ex.getBindingResult()
            .getFieldErrors()
            .stream()
            .findFirst()
            .map(error -> error.getDefaultMessage())
            .orElse("Invalid request");

    return ResponseEntity
            .badRequest()
            .body(Map.of(
                    "error", "VALIDATION_ERROR",
                    "message", message
            ));
}
}
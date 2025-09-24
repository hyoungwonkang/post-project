package com.example.post_project.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import jakarta.servlet.http.HttpServletRequest;

@RestControllerAdvice
public class ApiExceptionHandler {

    @ExceptionHandler(value = Exception.class)
    public ResponseEntity<ErrorResponse> handleException(HttpServletRequest req, Exception ex) {
        return handleException(req, ex, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(value = ArticleNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleException(HttpServletRequest req, ArticleNotFoundException ex) {
        return handleException(req, ex, HttpStatus.BAD_REQUEST);
    }

    // Overloaded private method for handling exceptions
    private ResponseEntity<ErrorResponse> handleException(HttpServletRequest req, Exception ex, HttpStatus status) {
        System.out.println("url: " + req.getRequestURL() + ", method: " + req.getMethod() + ", ex : " + ex.getMessage());

        ErrorResponse response = ErrorResponse.builder()
                                    .code(String.valueOf(status.value()))
                                    .message(ex.getMessage())
                                    .build();
        return ResponseEntity.ok().body(response);
    }
}

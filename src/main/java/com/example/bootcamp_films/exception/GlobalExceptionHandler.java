package com.example.bootcamp_films.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.http.converter.HttpMessageNotReadableException;

import java.time.LocalDateTime;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<ErrorResponse> handleJsonParse(HttpMessageNotReadableException ex) {
        String message = ex.getMostSpecificCause().getMessage(); // pega a mensagem detalhada
        ErrorResponse error = new ErrorResponse(LocalDateTime.now(), message);
        return ResponseEntity.badRequest().body(error);
    }

    // Classe interna para resposta de erro
    public static class ErrorResponse {
        private LocalDateTime timestamp;
        private String message;

        public ErrorResponse(LocalDateTime timestamp, String message) {
            this.timestamp = timestamp;
            this.message = message;
        }

        public LocalDateTime getTimestamp() {
            return timestamp;
        }

        public String getMessage() {
            return message;
        }
    }
}
package com.example.bill_generation_system.handler;

import com.twilio.exception.ApiException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class ValidationExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<?> handleValidationException(MethodArgumentNotValidException ex){
        Map<String, String> errors = new HashMap<>();

        ex.getBindingResult().getFieldErrors().forEach(error -> {
            errors.put(error.getField(), error.getDefaultMessage());
        });

        return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(ApiException.class)
    public ResponseEntity<String> handleTwilioApiException(ApiException ex) {

        if (ex.getStatusCode() == 400 && ex.getMessage().contains("exceeded the 9 daily messages limit")) {
            return new ResponseEntity<>("Twilio Trial Limit Reached: Only 9 messages allowed per day. Upgrade your account.", HttpStatus.TOO_MANY_REQUESTS);
        }

        return new ResponseEntity<>("Twilio Error: " + ex.getMessage(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(DataIntegrityViolationException.class)
    public ResponseEntity<?> handleDuplicate(DataIntegrityViolationException ex) {
        String root = ex.getMostSpecificCause().getMessage().toLowerCase();

        String error = "Duplicate entry found";

        if (root.contains("uk_email")) {
            error = "Email already exists!";
        } else if (root.contains("uk_phone")) {
            error = "Phone number already exists!";
        }

        return ResponseEntity.status(HttpStatus.CONFLICT).body(Map.of("error", error));
    }

}

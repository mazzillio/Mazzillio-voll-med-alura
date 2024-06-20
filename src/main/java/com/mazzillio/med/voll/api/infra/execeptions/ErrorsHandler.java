package com.mazzillio.med.voll.api.infra.execeptions;

import com.mazzillio.med.voll.api.domain.ServiceExceptionValidation;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.List;

@RestControllerAdvice
public class ErrorsHandler {
    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<Object> handlerError404() {
        return ResponseEntity.notFound().build();
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Object> handlerError400(MethodArgumentNotValidException exception) {
        List<FieldError> errors = exception.getFieldErrors();
        return ResponseEntity.badRequest().body(errors.stream().map(ValidationDataError::new).toList());
    }

    @ExceptionHandler(ServiceExceptionValidation.class)
    public ResponseEntity<Object> handleExceptionService(ServiceExceptionValidation exception) {
        return ResponseEntity.badRequest().body(exception.getMessage());
    }

    private record ValidationDataError(String message) {
        ValidationDataError(FieldError error) {
            this(error.getField() + " " + error.getDefaultMessage());
        }
    }
}

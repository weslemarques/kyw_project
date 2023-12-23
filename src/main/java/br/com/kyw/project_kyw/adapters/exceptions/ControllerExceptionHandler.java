package br.com.kyw.project_kyw.adapters.exceptions;

import br.com.kyw.project_kyw.adapters.exceptions.StandardError;
import br.com.kyw.project_kyw.adapters.exceptions.validator.ValidatorError;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.time.Instant;

@ControllerAdvice
public class ControllerExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.UNPROCESSABLE_ENTITY)
    public ResponseEntity<StandardError> validationEntity(
            MethodArgumentNotValidException e,
            HttpServletRequest request) {
        HttpStatus status = HttpStatus.UNPROCESSABLE_ENTITY;
        ValidatorError err = new ValidatorError();

        err.setTimestamp(Instant.now());
        err.setStatus(status.value());
        err.setError("Validation exception");
        err.setMessage(e.getAllErrors().get(0).getDefaultMessage());
        err.setPath(request.getRequestURI());

        for (FieldError f : e.getBindingResult().getFieldErrors()) {
            err.addError(f.getField(), f.getDefaultMessage());
        }

        return ResponseEntity.status(status).body(err);
    }
}

package com.logistica.api.exceptionhandler.exceptionhandler;

import com.logistica.domain.exception.DomainException;
import com.logistica.domain.exception.EntityNotFoundException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;

@ControllerAdvice
public class ExceptionHandler extends ResponseEntityExceptionHandler {
    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {

        List<com.logistica.api.exceptionhandler.exceptionhandler.Problems.Fields> fieldsList = new ArrayList<>();

        for (ObjectError error : ex.getBindingResult().getAllErrors()) {
            String nameField = ((FieldError) error).getField();
            String message = error.getDefaultMessage();
            fieldsList.add(new com.logistica.api.exceptionhandler.exceptionhandler.Problems.Fields(nameField, message));
        }

        com.logistica.api.exceptionhandler.exceptionhandler.Problems problems = new com.logistica.api.exceptionhandler.exceptionhandler.Problems();
        problems.setStatus(status.value());
        problems.setHour(OffsetDateTime.now());
        problems.setTitle("Invalid arguments");
        problems.setFields(fieldsList);
        return handleExceptionInternal(ex, problems, headers, status, request);
    }

    @org.springframework.web.bind.annotation.ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<Object> handleEntityNotFoundException(EntityNotFoundException ex, WebRequest request) {

        HttpStatus status = HttpStatus.NOT_FOUND;

        com.logistica.api.exceptionhandler.exceptionhandler.Problems problems = new com.logistica.api.exceptionhandler.exceptionhandler.Problems();
        problems.setStatus(status.value());
        problems.setHour(OffsetDateTime.now());
        problems.setTitle(ex.getMessage());

        return handleExceptionInternal(ex, problems, new HttpHeaders(), status, request);
    }

    @org.springframework.web.bind.annotation.ExceptionHandler(DomainException.class)
    public ResponseEntity<Object> handleDomain(DomainException ex, WebRequest request) {

        HttpStatus status = HttpStatus.BAD_REQUEST;

        com.logistica.api.exceptionhandler.exceptionhandler.Problems problems = new com.logistica.api.exceptionhandler.exceptionhandler.Problems();
        problems.setStatus(status.value());
        problems.setHour(OffsetDateTime.now());
        problems.setTitle(ex.getMessage());

        return handleExceptionInternal(ex, problems, new HttpHeaders(), status, request);
    }
}
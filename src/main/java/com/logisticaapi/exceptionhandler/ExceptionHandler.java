package com.logisticaapi.exceptionhandler;

import com.logisticaapi.domain.exception.DomainException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@ControllerAdvice
public class ExceptionHandler extends ResponseEntityExceptionHandler {
    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {

        List<Problems.Fields> fieldsList = new ArrayList<>();

        for (ObjectError error : ex.getBindingResult().getAllErrors()) {
            String nameField = ((FieldError) error).getField();
            String message = error.getDefaultMessage();
            fieldsList.add(new Problems.Fields(nameField, message));
        }

        Problems problems = new Problems();
        problems.setStatus(status.value());
        problems.setHour(LocalDateTime.now());
        problems.setTitle("Invalid arguments");
        problems.setFields(fieldsList);
        return handleExceptionInternal(ex, problems, headers, status, request);
    }

    @org.springframework.web.bind.annotation.ExceptionHandler(DomainException.class)
    public ResponseEntity<Object> handleDomain(DomainException ex, WebRequest request) {

        HttpStatus status = HttpStatus.BAD_REQUEST;

        Problems problems = new Problems();
        problems.setStatus(status.value());
        problems.setHour(LocalDateTime.now());
        problems.setTitle(ex.getMessage());

        return handleExceptionInternal(ex, problems, new HttpHeaders(), status, request);
    }
}
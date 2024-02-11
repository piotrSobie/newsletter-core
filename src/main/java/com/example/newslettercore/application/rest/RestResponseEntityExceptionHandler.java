package com.example.newslettercore.application.rest;

import com.example.newslettercore.domain.exception.CantLoginException;
import com.example.newslettercore.domain.exception.NewsletterCoreObjectNotFoundException;
import com.example.newslettercore.domain.exception.UserEmailInvalidException;
import com.example.newslettercore.domain.exception.UserNameInvalidException;
import com.example.newslettercore.domain.exception.UserPasswordInvalidException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class RestResponseEntityExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(value = {NewsletterCoreObjectNotFoundException.class})
    protected ResponseEntity<Object> handleNotFound(RuntimeException e, WebRequest request) {

        String bodyOfResponse = e.getMessage();
        return handleExceptionInternal(e, bodyOfResponse, new HttpHeaders(), HttpStatus.NOT_FOUND, request);
    }

    @ExceptionHandler(value = {CantLoginException.class})
    protected ResponseEntity<Object> handleCantLogin(RuntimeException e, WebRequest request) {

        String bodyOfResponse = e.getMessage();
        return handleExceptionInternal(e, bodyOfResponse, new HttpHeaders(), HttpStatus.UNAUTHORIZED, request);
    }

    @ExceptionHandler(value = {UserNameInvalidException.class, UserPasswordInvalidException.class, UserEmailInvalidException.class})
    protected ResponseEntity<Object> handleBadRequest(RuntimeException e, WebRequest request) {

        String bodyOfResponse = e.getMessage();
        return handleExceptionInternal(e, bodyOfResponse, new HttpHeaders(), HttpStatus.BAD_REQUEST, request);
    }
}

package com.url_shortener.rest.controller;

import com.url_shortener.excetion.BusinessException;
import static org.springframework.http.HttpStatus.*;

import com.url_shortener.rest.ApiErrors;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ApplicationControllerAdvice {

    @ExceptionHandler(BusinessException.class)
    @ResponseStatus(BAD_REQUEST)
    public ApiErrors handleBusinessException(BusinessException e) {
        String errorMessage = e.getMessage();
        return new ApiErrors(errorMessage);
    }

}

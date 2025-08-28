package com.example.web_app.exceptions;

import org.springframework.http.HttpStatus;

import lombok.Getter;

@Getter
public class ApiException extends RuntimeException{

    private final HttpStatus httpStatus;

    public ApiException(String message, HttpStatus httpStatus){
        super(message);
        this.httpStatus = httpStatus;
    }
}

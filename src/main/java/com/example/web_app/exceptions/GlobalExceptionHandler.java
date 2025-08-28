package com.example.web_app.exceptions;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(ApiException.class)
    public ResponseEntity<?> handlerException(ApiException ex){
        return new ResponseEntity<>(ErrorResponse
            .builder()
            .error(ex.getMessage())
            .build(),
            ex.getHttpStatus()
    );
    }
}

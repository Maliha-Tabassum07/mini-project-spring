package com.maliha.miniproject.controller;

import com.maliha.miniproject.exception.CustomException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler({NullPointerException.class})
    public ResponseEntity<?> handleNullPointerException(){
        return new ResponseEntity<>(new CustomException().getMessage(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler({RuntimeException.class})
    public ResponseEntity<?> handleRuntimeException(){
        return new ResponseEntity<>(new CustomException("Cannot perform this action").getMessage(), HttpStatus.BAD_REQUEST);
    }

}

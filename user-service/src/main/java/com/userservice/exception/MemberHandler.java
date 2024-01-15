package com.userservice.exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
@Slf4j
public class MemberHandler {

    @ExceptionHandler(value = MethodArgumentNotValidException.class)
    public ResponseEntity<?> validException(MethodArgumentNotValidException e) {

        String exceptionMessage = e.getBindingResult().getFieldErrors().get(0).getDefaultMessage();

        log.info("Exception Handler");
        return ResponseEntity.status(HttpStatus.CONFLICT).body(exceptionMessage);
    }
}

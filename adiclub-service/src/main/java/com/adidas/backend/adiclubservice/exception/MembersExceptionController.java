package com.adidas.backend.adiclubservice.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class MembersExceptionController {

    @ExceptionHandler(value = MembersNotFoundException.class)
    public ResponseEntity<Object> exception(MembersNotFoundException exception) {
        return new ResponseEntity<>("Member not found", HttpStatus.NOT_FOUND);
    }

}

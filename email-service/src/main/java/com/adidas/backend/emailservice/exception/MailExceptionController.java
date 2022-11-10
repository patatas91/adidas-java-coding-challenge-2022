package com.adidas.backend.emailservice.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class MailExceptionController {

    @ExceptionHandler(value = NoDataException.class)
    public ResponseEntity<Object> exception(NoDataException exception) {
        return new ResponseEntity<>("You must wait for any member subscription", HttpStatus.INTERNAL_SERVER_ERROR);
    }

}

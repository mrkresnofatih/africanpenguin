package dev.mrkresnofatih.africanpenguin.controllers;

import dev.mrkresnofatih.africanpenguin.constants.ApplicationErrorCodes;
import dev.mrkresnofatih.africanpenguin.exceptions.IncorrectCredentialsException;
import dev.mrkresnofatih.africanpenguin.models.ResponseHandler;
import dev.mrkresnofatih.africanpenguin.models.ResponsePackage;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ErrorController {
    @ExceptionHandler(value = IncorrectCredentialsException.class)
    public ResponseEntity<ResponsePackage<Object>> HandleIncorrectCredentialsException() {
        return respondError(ApplicationErrorCodes.INCORRECT_CREDENTIALS);
    }

    private ResponseEntity<ResponsePackage<Object>> respondError(String errorCode) {
        var responsePackage = new ResponseHandler<Object>().WrapFailure(errorCode);
        return new ResponseEntity<>(responsePackage, HttpStatus.BAD_REQUEST);
    }
}

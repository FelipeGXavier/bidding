package com.licitacao.core;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.ArrayList;
import java.util.List;

@ControllerAdvice
public class ExceptionHandlerRest {

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    @ExceptionHandler({MethodArgumentNotValidException.class})
    public BindErrorResponse bindError(MethodArgumentNotValidException e) {
        List<FieldError> errorList = e.getBindingResult().getFieldErrors();
        List<BindErrorResponse.ErrorDetails> errorDetails = new ArrayList<>();
        for (FieldError err : errorList) {
            BindErrorResponse.ErrorDetails error = new BindErrorResponse.ErrorDetails();
            error.setField(err.getField());
            error.setError(err.getDefaultMessage());
            errorDetails.add(error);
        }
        BindErrorResponse bindErrorResponse = new BindErrorResponse();
        bindErrorResponse.setErrors(errorDetails);
        return bindErrorResponse;
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    @ExceptionHandler(DataIntegrityViolationException.class)
    public ResponseEntity<?> handleViolationIntegrity() {
        return new ResponseEntity<>(new CustomExceptionMessage().setSuccess(false).setMessage("Objeto já existente"), HttpStatus.BAD_REQUEST);
    }
}

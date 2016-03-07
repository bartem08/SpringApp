package com.baranovskiy.webapp.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;

public class Response {

    public static ResponseEntity<ResponseJSON> createResponse(BindingResult result, HttpStatus status) {
        ResponseJSON responseJSON = new ResponseJSON(result.getFieldError().getDefaultMessage());
        return new ResponseEntity<>(responseJSON, status);
    }

    public static ResponseEntity<ResponseJSON> createResponse(String message, HttpStatus status) {
        ResponseJSON responseJSON = new ResponseJSON(message);
        return new ResponseEntity<>(responseJSON, status);
    }

}

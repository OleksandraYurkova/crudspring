package com.example.crud_spring;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public class StudentNotFound extends RuntimeException {
    public ResponseEntity<String> StudentNotFound(String message) {
       return new ResponseEntity<String>(message, HttpStatus.NOT_FOUND);
    }
}

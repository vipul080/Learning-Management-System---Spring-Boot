package com.lms.exception;

public class AlreadyEnrolledException extends RuntimeException {

    public AlreadyEnrolledException(String message) {
        super(message);
    }
}
package com.example.Task3.exception;

public class UnauthorizedAccessException  extends RuntimeException {
    public UnauthorizedAccessException(String message) {
        super(message);
    }
}

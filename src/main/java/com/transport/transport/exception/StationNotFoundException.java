package com.transport.transport.exception;

public class StationNotFoundException extends RuntimeException {
    public StationNotFoundException(String message,Long id) {
        super(message + id);
    }

}

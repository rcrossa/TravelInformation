package com.transport.transport.exception;

public class PathNotFoundException extends RuntimeException{
    public PathNotFoundException(String message,Long id) {
        super(message + id);
    }
}

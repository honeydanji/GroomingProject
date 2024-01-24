package com.albumservice.exception;

public class UniqueTitleException extends RuntimeException{
    public UniqueTitleException(String message) {
        super(message);
    }
}

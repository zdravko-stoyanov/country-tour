package com.country.demo.exception;

public class BadRequestException extends RuntimeException {

    private static final long serialVersionUID = -1139365480362638469L;

    public BadRequestException(String message) {
        super(message);
    }
}

package com.country.demo.exception;

public class NotFoundException extends RuntimeException {

    private static final long serialVersionUID = 6233283940241519292L;

    public NotFoundException(String message) {
        super(message);
    }
}

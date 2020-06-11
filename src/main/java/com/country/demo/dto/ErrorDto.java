package com.country.demo.dto;

import org.springframework.http.HttpStatus;

import java.util.Objects;

public class ErrorDto {

    private int code;
    private String message;

    public ErrorDto(String message, HttpStatus code) {
        this.message = message;
        this.code = code.value();
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ErrorDto errorDto = (ErrorDto) o;
        return Objects.equals(code, errorDto.code) &&
                Objects.equals(message, errorDto.message);
    }

    @Override
    public int hashCode() {
        return Objects.hash(code, message);
    }

    @Override
    public String toString() {
        return "ErrorDto{" +
                "code='" + code + '\'' +
                ", message='" + message + '\'' +
                '}';
    }
}

package com.example.demo.exception;

public class CustomException extends RuntimeException{
    private final int code;
    private final String message;

    public CustomException(Validation validation) {
        super(validation.getMessage());
        this.code = validation.getCode();
        this.message = validation.getMessage();
    }

    public int getCode() {
        return code;
    }


    public String getMessage() {
        return message;
    }
}

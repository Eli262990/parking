package com.example.demo.exception;

public enum Validation {
    LICENSE_PLATE_IS_NOT_EMPTY(100, "msg_100100"),
    LICENSE_PLATE_FORMAT(101, "msg_100101"),
    LICENSE_PLATE_IS_EXIST(102, "msg_100102"),
    CAR_TYPE_IS_EXIST(103,"msg_100103"),
    CAR_TYPE_NOT_FOUND(104, "msg_100104"),
    CAR_TYPE_NOT_FOUND_WITH_ID(105,  "msg_100105");
    private final int code;
    private final String message;


    Validation(int code, String message) {
        this.code = code;
        this.message = message;
    }


    public int getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }
}

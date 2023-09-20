package com.example.rewardsrestful.exception;

public class MonthNotValidException extends RuntimeException {

    public MonthNotValidException(String message) {
        super(message);
    }

    @Override
    public String getMessage() {
        return super.getMessage();
    }
}

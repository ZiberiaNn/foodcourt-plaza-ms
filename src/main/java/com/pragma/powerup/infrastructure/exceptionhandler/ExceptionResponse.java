package com.pragma.powerup.infrastructure.exceptionhandler;

public enum ExceptionResponse {
    NO_DATA_FOUND("No data found for the requested petition"),
    INVALID_PHONE("The phone format is invalid. It must start with a '+' followed by a maximum of 13 numbers"),
    INVALID_FORMAT("The format of at least one JSON attribute value is invalid. Check that the input data is correct"),
    INVALID_NAME("The name of the restaurant must be a string that does not contain numbers only"),
    INVALID_NIT("NIT must be a number"),
    USER_NOT_OWNER("The user with the entered ID is not the owner"),
    FEIGN_BAD_REQUEST("Check that the request JSON body is correct");


    private final String message;

    ExceptionResponse(String message) {
        this.message = message;
    }

    public String getMessage() {
        return this.message;
    }
}
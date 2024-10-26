package com.corcino.payment_food.domain.exception;

public class InvalidFieldException extends RuntimeException {

    public InvalidFieldException(String message) {
        super(message);
    }

}

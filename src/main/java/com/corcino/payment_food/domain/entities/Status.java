package com.corcino.payment_food.domain.entities;

import com.corcino.payment_food.domain.exception.InvalidFieldException;

public enum Status {

    CREATED,
    CONFIRMED,
    CANCELLED;

    public static Status confirmStatus(String statusName) {
        for (Status status : Status.values()) {
            if (status.name().equals(statusName)) {
                return status;
            }
        }
        throw new InvalidFieldException("Invalid status: " + statusName);
    }

}

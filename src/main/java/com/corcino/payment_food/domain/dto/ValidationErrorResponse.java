package com.corcino.payment_food.domain.dto;

import lombok.Getter;
import lombok.experimental.SuperBuilder;

@Getter
@SuperBuilder
public class ValidationErrorResponse extends ErrorResponse {

    private String field;

}

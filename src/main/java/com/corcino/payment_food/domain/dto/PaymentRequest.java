package com.corcino.payment_food.domain.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class PaymentRequest {

    @NotBlank
    private BigDecimal amount;

    @NotBlank
    private String name;

    @NotBlank
    private String cardNumber;

    @NotBlank
    private String expiryDate;

    @NotBlank
    private String cvv;

    @NotBlank
    private String status;

    @NotBlank
    private Long orderId;

    @NotBlank
    private Long paymentTypeId;

}

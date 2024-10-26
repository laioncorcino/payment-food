package com.corcino.payment_food.domain.dto;

import com.corcino.payment_food.domain.entities.Status;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class PaymentResponse {

    private Long paymentId;
    private BigDecimal amount;
    private String name;
    private String cardNumber;
    private String expiryDate;
    private String cvv;
    private Status status;
    private Long orderId;
    private Long paymentTypeId;

}

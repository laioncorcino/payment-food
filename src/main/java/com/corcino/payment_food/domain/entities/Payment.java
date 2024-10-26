package com.corcino.payment_food.domain.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.math.BigDecimal;

@Entity
@Data
public class Payment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long paymentId;

    @NotNull
    @Positive
    private BigDecimal amount;

    @NotBlank
    private String name;

    @NotBlank
    @Size(max = 19)
    private String cardNumber;

    @NotBlank
    @Size(max = 7)
    private String expiryDate;

    @NotBlank
    @Size(min = 3, max = 3)
    private String cvv;

    @NotNull
    @Enumerated(EnumType.STRING)
    private Status status;

    @NotNull
    private Long orderId;

    @NotNull
    private Long paymentTypeId;

}

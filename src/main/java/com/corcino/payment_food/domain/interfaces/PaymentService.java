package com.corcino.payment_food.domain.interfaces;

import com.corcino.payment_food.domain.dto.PaymentRequest;
import com.corcino.payment_food.domain.dto.PaymentResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface PaymentService {

    PaymentResponse create(PaymentRequest paymentRequest);
    Page<PaymentResponse> getPayments(Pageable pageable);
    PaymentResponse getPaymentById(Long paymentId);
    void deletePayment(Long paymentId);

}

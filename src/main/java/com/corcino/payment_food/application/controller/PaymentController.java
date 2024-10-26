package com.corcino.payment_food.application.controller;

import com.corcino.payment_food.domain.dto.PaymentRequest;
import com.corcino.payment_food.domain.dto.PaymentResponse;
import com.corcino.payment_food.domain.interfaces.PaymentService;
import com.corcino.payment_food.domain.services.PaymentServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

import static org.springframework.data.domain.Sort.Direction.ASC;

@RestController
@RequestMapping("/api/v1/payment")
public class PaymentController {

    private final PaymentService paymentService;

    @Autowired
    public PaymentController(PaymentService paymentService) {
        this.paymentService = paymentService;
    }

    @PostMapping
    public ResponseEntity<PaymentResponse> createPayment(@RequestBody PaymentRequest paymentRequest, UriComponentsBuilder uriBuilder) {
        PaymentResponse paymentResponse = paymentService.create(paymentRequest);
        URI path = uriBuilder.path("/api/v1/payment/{id}").buildAndExpand(paymentResponse.getPaymentId()).toUri();
        return ResponseEntity.created(path).body(paymentResponse);
    }

    @GetMapping
    public ResponseEntity<Page<PaymentResponse>> listPayments(@PageableDefault(sort = "paymentId", direction = ASC) Pageable pageable) {
        return ResponseEntity.ok(paymentService.getPayments(pageable));
    }

    @GetMapping("/{paymentId}")
    public ResponseEntity<PaymentResponse> getPayment(@PathVariable Long paymentId) {
        return ResponseEntity.ok(paymentService.getPaymentById(paymentId));
    }

    @DeleteMapping("/{paymentId}")
    public ResponseEntity<Void> deletePayment(@PathVariable Long paymentId) {
        paymentService.deletePayment(paymentId);
        return ResponseEntity.noContent().build();
    }

}

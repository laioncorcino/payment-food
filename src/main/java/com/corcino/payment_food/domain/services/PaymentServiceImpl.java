package com.corcino.payment_food.domain.services;

import com.corcino.payment_food.domain.dto.PaymentRequest;
import com.corcino.payment_food.domain.dto.PaymentResponse;
import com.corcino.payment_food.domain.entities.Payment;
import com.corcino.payment_food.domain.entities.Status;
import com.corcino.payment_food.domain.exception.EntityNotFoundException;
import com.corcino.payment_food.domain.interfaces.PaymentService;
import com.corcino.payment_food.infrastructure.repository.PaymentRepository;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@Slf4j
public class PaymentServiceImpl implements PaymentService {

    private final PaymentRepository paymentRepository;
    private final ModelMapper mapper;

    @Autowired
    public PaymentServiceImpl(PaymentRepository paymentRepository, ModelMapper mapper) {
        this.paymentRepository = paymentRepository;
        this.mapper = mapper;
    }

    public PaymentResponse create(PaymentRequest paymentRequest) {
        Payment payment = mapper.map(paymentRequest, Payment.class);
        payment.setStatus(Status.CREATED);
        Payment paymentSaved = save(payment);
        return mapper.map(paymentSaved, PaymentResponse.class);
    }

    public Page<PaymentResponse> getPayments(Pageable pageable) {
        return paymentRepository
                .findAll(pageable)
                .map(e -> mapper.map(e, PaymentResponse.class));
    }

    public PaymentResponse getPaymentById(Long paymentId) {
        Payment payment = getPayment(paymentId);
        return mapper.map(payment, PaymentResponse.class);
    }

    public void deletePayment(Long paymentId) {
        getPayment(paymentId);
        paymentRepository.deleteById(paymentId);
    }


    private Payment save(Payment payment) {
        try {
            log.info("Saving payment {}", payment);
            return paymentRepository.save(payment);
        }
        catch (DataIntegrityViolationException e) {
            log.error("Error saving payment {}", payment, e);
            throw new DataIntegrityViolationException("Duplicated key --- " + e.getMessage());
        }
        catch (Exception e) {
            log.error("Error saving payment {}", payment, e);
            throw new RuntimeException(e.getMessage());
        }
    }

    private Payment getPayment(Long paymentId) {
        log.info("Get payment with id {}", paymentId);
        Optional<Payment> payment = paymentRepository.findById(paymentId);

        return payment.orElseThrow(() -> {
            log.info("Payment with id {} not found", paymentId);
            return new EntityNotFoundException(String.format("Payment with id %s not found", paymentId));
        });
    }

}

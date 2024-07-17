package com.scaler.project.paymentservice.paymentservice.services;

import com.scaler.project.paymentservice.paymentservice.paymmentgateways.PaymentGateway;
import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Service;

@Service
@Getter
@Setter
public class PaymentService{
    private PaymentGateway paymentGateway;

    public PaymentService(PaymentGateway paymentGateway) {
        this.paymentGateway = paymentGateway;
    }

public String generatePaymentLink(String orderId, Long amount, String currency, String email) {
        return paymentGateway.initiatePayment(orderId, amount, currency, email);
    }

}

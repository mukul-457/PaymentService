package com.scaler.project.paymentservice.paymentservice.paymmentgateways;

public interface PaymentGateway {

    public String initiatePayment(String orderId, Long amount, String currency, String email);
}

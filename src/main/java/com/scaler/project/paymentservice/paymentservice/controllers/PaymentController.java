package com.scaler.project.paymentservice.paymentservice.controllers;

import com.scaler.project.paymentservice.paymentservice.dtos.InitiatePaymentDto;
import com.scaler.project.paymentservice.paymentservice.services.PaymentService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/payments")
public class PaymentController {

    private PaymentService paymentService;

    public PaymentController(PaymentService paymentService) {
        this.paymentService = paymentService;
    }

    @PostMapping
    public  String generatePyamentLink(@RequestBody InitiatePaymentDto paymentDto) {
        return paymentService.generatePaymentLink(paymentDto.getOrderId(),
                paymentDto.getAmount(), paymentDto.getCurrency(), paymentDto.getEmail());
    }
}

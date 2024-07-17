package com.scaler.project.paymentservice.paymentservice.dtos;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class InitiatePaymentDto {
    private String orderId;
    private Long amount;
    private String currency;
    private String email;
}

package com.scaler.project.paymentservice.paymentservice.paymmentgateways;

import com.stripe.Stripe;
import com.stripe.exception.StripeException;
import com.stripe.model.PaymentLink;
import com.stripe.model.Price;
import com.stripe.param.PaymentLinkCreateParams;
import com.stripe.param.PriceCreateParams;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class StripePaymentGateway implements PaymentGateway{

    @Value("${stripe.key}")
    private String key;

    public String initiatePayment(String orderId, Long amount, String currency, String email) {
        Stripe.apiKey = key;
        PriceCreateParams priceParams =
                PriceCreateParams.builder()
                        .setUnitAmount(amount)
                        .setCurrency(currency)
                        .setProductData(
                                PriceCreateParams.ProductData.builder()
                                        .setName("Order " + orderId)
                                        .build())
                        .build();

        Price price = null;
        try {
            price = Price.create(priceParams);
        }catch (StripeException e){
            e.printStackTrace();
            throw new RuntimeException("price creation failed");
        }

        PaymentLinkCreateParams paymentParams =
                PaymentLinkCreateParams.builder()
                        .addLineItem(
                                PaymentLinkCreateParams.LineItem.builder()
                                        .setPrice(price.getId())
                                        .setQuantity(1L)
                                        .build()).setAfterCompletion(
                                                PaymentLinkCreateParams
                                                        .AfterCompletion.
                                                        builder().
                                                        setRedirect(
                                                                PaymentLinkCreateParams
                                                                        .AfterCompletion
                                                                        .Redirect.builder().
                                                                        setUrl("https://www.youtube.com")
                                                                        .build())
                                                        .setType(PaymentLinkCreateParams.AfterCompletion.Type.REDIRECT)
                                                        .build()).build();

        PaymentLink paymentLink = null;
        try {
            paymentLink = PaymentLink.create(paymentParams);
        }catch (StripeException e){
            e.printStackTrace();
            throw new RuntimeException("payment link creation failed");
        }
        return  paymentLink.getUrl();
    }
}

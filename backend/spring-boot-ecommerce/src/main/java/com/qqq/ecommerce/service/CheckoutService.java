package com.qqq.ecommerce.service;

import com.qqq.ecommerce.dto.PaymentInfo;
import com.qqq.ecommerce.dto.Purchase;
import com.qqq.ecommerce.dto.PurchaseResponse;
import com.stripe.model.PaymentIntent;

/**
 * Created by QQQ on 2022/8/4 22:43
 */
public interface CheckoutService {

    PurchaseResponse placeOrder(Purchase purchase);

    PaymentIntent createPaymentIntent(PaymentInfo paymentInfo) throws Exception;
}

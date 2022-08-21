package com.qqq.ecommerce.controller;

import com.qqq.ecommerce.dto.PaymentInfo;
import com.qqq.ecommerce.dto.Purchase;
import com.qqq.ecommerce.dto.PurchaseResponse;
import com.qqq.ecommerce.service.CheckoutService;
import com.stripe.exception.StripeException;
import com.stripe.model.PaymentIntent;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * Created by QQQ on 2022/8/5 14:53
 */
//@Controller
//@CrossOrigin("http://localhost:4200")
//@RequestMapping("/api/checkout")
//public class CheckoutController {
//
//    private CheckoutService checkoutService;
//
//    @Autowired
//    public CheckoutController(CheckoutService checkoutService){
//        this.checkoutService = checkoutService;
//    }
//
//    @PostMapping("/purchase")
//    public PurchaseResponse placeOrders(@RequestBody Purchase purchase){
//
//        PurchaseResponse purchaseResponse = checkoutService.placeOrder(purchase);
//
//        return purchaseResponse;
//    }
//}

//@CrossOrigin("http://localhost:4200")
@RestController
@RequestMapping("/api/checkout")
public class CheckoutController {

    private CheckoutService checkoutService;

    public CheckoutController(CheckoutService checkoutService) {
        this.checkoutService = checkoutService;
    }

    @PostMapping("/purchase")
    public PurchaseResponse placeOrder(@RequestBody Purchase purchase) {

        PurchaseResponse purchaseResponse = checkoutService.placeOrder(purchase);

        return purchaseResponse;
    }

    @PostMapping("/payment-intent")
    public ResponseEntity<String> createPayment(@RequestBody PaymentInfo paymentInfo) throws Exception {

        //向stripe 发送 paymentIntent
        PaymentIntent paymentIntent = checkoutService.createPaymentIntent(paymentInfo);

        //收到来自stripe的返回值，其中包含client_secret
        String paymentStr = paymentIntent.toJson();

        //返回paymentIntent 给Angular Front end
        return new ResponseEntity<>(paymentStr, HttpStatus.OK);
    }

}

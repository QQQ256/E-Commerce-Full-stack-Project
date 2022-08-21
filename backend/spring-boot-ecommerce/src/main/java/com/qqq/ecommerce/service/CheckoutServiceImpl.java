package com.qqq.ecommerce.service;

import com.qqq.ecommerce.dao.CustomerRepository;
import com.qqq.ecommerce.dto.PaymentInfo;
import com.qqq.ecommerce.dto.Purchase;
import com.qqq.ecommerce.dto.PurchaseResponse;
import com.qqq.ecommerce.entity.Customer;
import com.qqq.ecommerce.entity.Order;
import com.qqq.ecommerce.entity.OrderItem;
import com.stripe.Stripe;
import com.stripe.model.PaymentIntent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.*;

/**
 * Created by QQQ on 2022/8/4 22:44
 */
@Service
public class CheckoutServiceImpl implements CheckoutService{

    private CustomerRepository customerRepository;//需要使用到JPA

    @Autowired
    public CheckoutServiceImpl(CustomerRepository customerRepository, @Value("${stripe.key.secret}") String secretKey){
        this.customerRepository = customerRepository;

        //初始化secret key
        Stripe.apiKey = secretKey;
    }

    @Override
    @Transactional
    public PurchaseResponse placeOrder(Purchase purchase) {

        //从dto拿order的数据
        Order order = purchase.getOrder();

        //产生trace number
        String orderTrackingNumber = generateOrderTrackingNumber();
        order.setOrderTrackingNumber(orderTrackingNumber);

        //使用orderItem填order
        Set<OrderItem> orderItems = purchase.getOrderItems();
        orderItems.forEach(item -> order.addItemsToOrder(item));//foreach新写法

        //使用billing 和 shipping 地址填order
        order.setBillingAddress(purchase.getBillingAddress());
        order.setShippingAddress(purchase.getShippingAddress());

        //使用用户填order
        Customer customer = purchase.getCustomer();

        //check if it's an existing customer
        String email = customer.getEmail();

        Customer customerFromDB = customerRepository.findByEmail(email);

        if(customerFromDB != null){
            customer = customerFromDB;
        }
        customer.addOrderToCustomer(order);

        //保存到数据库中
        customerRepository.save(customer);

        //返回一个response，使用后Lombok的@Data自动生成的构造器
        return new PurchaseResponse(orderTrackingNumber);
    }

    private String generateOrderTrackingNumber() {
        //random + hard to guess number
        //generate a random UUID:通用唯一识别码（英语：Universally Unique Identifier，缩写：UUID）是用于计算机体系中以识别信息的一个128位标识符。
        return UUID.randomUUID().toString();
    }



    @Override
    public PaymentIntent createPaymentIntent(PaymentInfo paymentInfo) throws Exception {
        List<String> paymentMethodTypes = new ArrayList<>();
        paymentMethodTypes.add("card");

        Map<String, Object> params = new HashMap<>();
        params.put("amount", paymentInfo.getAmount());
        params.put("currency", paymentInfo.getCurrency());
        params.put("payment_method_types", paymentMethodTypes);
        params.put("description", "shopPurchase");
        params.put("receipt_email", paymentInfo.getReceiptEmail());

        return PaymentIntent.create(params);
    }
}

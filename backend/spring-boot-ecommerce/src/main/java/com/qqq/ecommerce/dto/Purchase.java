package com.qqq.ecommerce.dto;

import com.qqq.ecommerce.entity.Address;
import com.qqq.ecommerce.entity.Customer;
import com.qqq.ecommerce.entity.Order;
import com.qqq.ecommerce.entity.OrderItem;
import lombok.Data;

import java.util.Set;

/**
 * Created by QQQ on 2022/8/4 22:37
 * Data Transfer Object (DTO).
 * 存放要存储的所有数据的对象
 */
@Data
public class Purchase {
    //存储的数据有: Customer, Shipping Address, Billing Address, Order, OrderItem[]
    private Customer customer;
    private Address shippingAddress;
    private Address billingAddress;
    private Order order;
    private Set<OrderItem> orderItems;
}

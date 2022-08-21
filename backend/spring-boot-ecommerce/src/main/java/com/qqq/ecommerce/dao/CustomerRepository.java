package com.qqq.ecommerce.dao;

import com.qqq.ecommerce.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by QQQ on 2022/8/4 22:41
 */

public interface CustomerRepository extends JpaRepository<Customer, Long> {
    //customer has a list of orders

    //通过email找customer
    Customer findByEmail(String theEmail);
}

package com.qqq.ecommerce.entity;

import lombok.Getter;
import lombok.Setter;
import org.aspectj.weaver.ast.Or;

import javax.persistence.*;
import java.math.BigDecimal;

/**
 * Created by QQQ on 2022/8/4 21:47
 */
@Entity
@Table(name = "order_item")
@Getter
@Setter
public class OrderItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "image_url")
    private String imageUrl;

    @Column(name = "unit_price")
    private BigDecimal unitPrice;

    @Column(name = "quantity")
    private int quantity;

    @Column(name = "product_id")
    private Long productId;

    //Order
    @ManyToOne
    @JoinColumn(name = "order_id")//另一个表指向本表的外键
    //FOREIGN KEY (`order_id`) REFERENCES `orders` (`id`),
    private Order order;
}

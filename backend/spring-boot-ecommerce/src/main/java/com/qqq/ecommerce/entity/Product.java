package com.qqq.ecommerce.entity;

import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;

/**
 * Created by QQQ on 2022/7/29 15:42
 */
@Entity
@Table(name = "product")
@Data//lombok!!!!!解放双手，再也不用写get 和 set啦
public class Product {

    @ManyToOne
    //@JoinColumn 注解的作用：用来指定与所操作实体或实体集合相关联的数据库表中的列字段。
    @JoinColumn(name = "category_id", nullable = false)
    private ProductCategory category;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "sku")
    private String sku;

    @Column(name = "name")
    private String name;

    @Column(name = "description")
    private String description;

    @Column(name = "unit_price")
    private BigDecimal unitPrice;

    @Column(name = "image_url")
    private String imageUrl;

    @Column(name = "active")
    private boolean active;

    @Column(name = "units_in_stock")
    private int unitsInStock;

    @Column(name = "date_created")
    @CreationTimestamp//Hibernate自动给你manage Timestamp
    private Date dateCreated;

    @Column(name = "last_updated")
    @CreationTimestamp
    private Date lastUpdated;

}

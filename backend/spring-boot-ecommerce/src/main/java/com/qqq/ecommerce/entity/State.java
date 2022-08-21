package com.qqq.ecommerce.entity;

import lombok.Data;

import javax.persistence.*;

/**
 * Created by QQQ on 2022/8/3 21:12
 */
@Entity
@Table(name = "state")
@Data
public class State {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "name")
    private String name;

    @ManyToOne
    @JoinColumn(name = "country_id")//外键
    private Country country;
}

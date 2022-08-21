package com.qqq.ecommerce.dto;

import lombok.Data;

/**
 * Created by QQQ on 2022/8/9 17:16
 */
@Data
public class PaymentInfo {
    private int amount;//stripe有自己的规则将小数￥转成想要的值
    private String currency;
    private String receiptEmail;
}

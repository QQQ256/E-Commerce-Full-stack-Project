package com.qqq.ecommerce.dto;

import lombok.Data;
import lombok.NonNull;

/**
 * Created by QQQ on 2022/8/4 22:39
 * 本身包含一个 orderTrackingNumber: string
 */
@Data
//send back java object as json
public class PurchaseResponse {

    //Lombok @Data 为final fields generate 构造器
    @NonNull
    private String orderTrackingNumber;

}

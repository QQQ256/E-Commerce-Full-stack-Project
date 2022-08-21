package com.qqq.ecommerce.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * Created by QQQ on 2022/8/7 17:32
 * 给CheckoutController配置allowOrigins和mapping用
 *
 */
@Configuration
public class MyAppConfig implements WebMvcConfigurer {
    @Value("${allowed.origins}")
    private String theAllowedOrigins;//将[] 改成了单字符串仍然OK

    @Value("${spring.data.rest.base-path}")
    private String basePath;

    @Override
    //这个函数的说明：Configure "global" cross-origin request processing. The configured CORS mappings apply to annotated controllers, functional endpoints, and static resources.
    //这样checkoutController这个restController就不用再写@CrossOrigin了
    public void addCorsMappings(CorsRegistry cors) {
        //set up cors mapping
        cors.addMapping(basePath + "/**").allowedOrigins(theAllowedOrigins);
    }
}

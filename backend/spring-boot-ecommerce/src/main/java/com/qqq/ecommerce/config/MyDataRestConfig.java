package com.qqq.ecommerce.config;

import com.qqq.ecommerce.entity.*;

import javax.persistence.metamodel.EntityType;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.rest.core.config.RepositoryRestConfiguration;
import org.springframework.data.rest.webmvc.config.RepositoryRestConfigurer;
import org.springframework.http.HttpMethod;
import org.springframework.web.servlet.config.annotation.CorsRegistry;

import javax.persistence.EntityManager;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 * Created by QQQ on 2022/7/29 16:50
 * 配置repository的config类
 */
@Configuration
@SpringBootApplication(scanBasePackages = "com.qqq.ecommerce")
public class MyDataRestConfig implements RepositoryRestConfigurer {

    private EntityManager entityManager;

    @Value("${allowed.origins}")
    private String[] theAllowedOrigins;//why string[]??? not string???

//    private String path;

    @Autowired
    public MyDataRestConfig(EntityManager theEntityManager){
        this.entityManager = theEntityManager;
    }

    @Override
    public void configureRepositoryRestConfiguration(RepositoryRestConfiguration config, CorsRegistry cors) {



        HttpMethod[] unsupportedActions = {HttpMethod.DELETE, HttpMethod.POST, HttpMethod.PUT, HttpMethod.PATCH};

        //disable这几个http request

        //(Product.class)
        disableHttpMethods(Product.class, config, unsupportedActions);

        //(ProductCategory.class)
        disableHttpMethods(ProductCategory.class, config, unsupportedActions);

        //(State.class)
        disableHttpMethods(State.class, config, unsupportedActions);

        //(Country.class)
        disableHttpMethods(Country.class, config, unsupportedActions);

        disableHttpMethods(Order.class, config, unsupportedActions);

        //写个internal method 去expose id
        exposeIds(config);

        //config cors mapping
        cors.addMapping(config.getBasePath() + "/**").allowedOrigins(theAllowedOrigins);
    }

    private void disableHttpMethods(Class theClass, RepositoryRestConfiguration config, HttpMethod[] unsupportedActions) {
        config.getExposureConfiguration()
                .forDomainType(theClass)
                .withItemExposure((metdata, httpMethods) -> httpMethods.disable(unsupportedActions))
                .withCollectionExposure((metdata, httpMethods) -> httpMethods.disable(unsupportedActions));
    }

    private void exposeIds(RepositoryRestConfiguration config){
        //先从entity manager那获取所有的entity class
        //entities最终会获取两个类，product和product-category
        Set<EntityType<?>> entities = entityManager.getMetamodel().getEntities();

        //创建entity types的数组
        List<Class> entityClasses = new ArrayList<>();

        //从上面那句里拿Class，将其加入list
        for(EntityType temp : entities){
            entityClasses.add(temp.getJavaType());
        }

        Class[] domainType = entityClasses.toArray(new Class[0]);

        config.exposeIdsFor(domainType);
    }
}

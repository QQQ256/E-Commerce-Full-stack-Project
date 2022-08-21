package com.qqq.ecommerce.dao;

import com.qqq.ecommerce.entity.ProductCategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.web.bind.annotation.CrossOrigin;

/**
 * Created by QQQ on 2022/7/29 16:17
 */
//                                  JSON entry - productCategory   actual reference: /product-category
@RepositoryRestResource(collectionResourceRel = "productCategory", path = "product-category")
//@CrossOrigin("http://localhost:4200")//允许来自该url的call
public interface ProductCategoryRepository extends JpaRepository<ProductCategory, Long> {
}

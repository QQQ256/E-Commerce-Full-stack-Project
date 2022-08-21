package com.qqq.ecommerce.dao;

import com.qqq.ecommerce.entity.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * Created by QQQ on 2022/7/29 16:16
 */
@CrossOrigin("http://localhost:4200")//允许来自该url的call
@RepositoryRestResource
public interface ProductRepository extends JpaRepository<Product, Long>{
    //通过id来从数据库搜相同category的products
    //Query Method --> findBy开头的，会自己生成个搜索语句来找这个id
    Page<Product> findByCategoryId(@RequestParam("id") Long id, Pageable pageable);

    Page<Product> findByNameContaining(@RequestParam("name") String name, Pageable pageable);

}
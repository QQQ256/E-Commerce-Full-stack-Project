package com.qqq.ecommerce.dao;

import com.qqq.ecommerce.entity.Order;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

/**
 * Created by QQQ on 2022/8/7 20:48
 */
@RepositoryRestResource
public interface OrderRepository extends JpaRepository<Order, Long> {
    //通过email找order，从而找到email的拥有者，也就是用这个号的人
    //是先找customer的email，再定位order的
    Page<Object> findByCustomerEmailOrderByDateCreatedDesc(@Param("email") String email, Pageable pageable);
    //别忘了将这个API给not expose了
}

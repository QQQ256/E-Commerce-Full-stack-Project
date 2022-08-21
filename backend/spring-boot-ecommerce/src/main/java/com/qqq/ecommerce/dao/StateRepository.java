package com.qqq.ecommerce.dao;

import com.qqq.ecommerce.entity.State;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.web.bind.annotation.CrossOrigin;

import java.util.List;

/**
 * Created by QQQ on 2022/8/3 21:33
 */
//@CrossOrigin("http://localhost:4200")
@RepositoryRestResource
public interface StateRepository extends JpaRepository<State, Integer> {

    // api/states/search/finByCountryCOde?code=IN/US/...
    List<State> findByCountryCode(@Param("code") String code);
}

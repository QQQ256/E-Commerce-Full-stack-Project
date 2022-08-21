package com.qqq.ecommerce.dao;

import com.qqq.ecommerce.entity.Country;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.web.bind.annotation.CrossOrigin;

/**
 * Created by QQQ on 2022/8/3 21:20
 */

//@CrossOrigin("http://localhost:4200")
@RepositoryRestResource(collectionResourceRel = "countries", path = "countries")
//                                                      class & primary key
public interface CountryRepository extends JpaRepository<Country, Integer> {

}

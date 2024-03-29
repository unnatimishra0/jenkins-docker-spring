package com.nagarro.training.repository;

import com.nagarro.training.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface ProductRepository extends JpaRepository<Product, Integer> {


    Product findByCode(String code);

    @Query("from Product where code like %:code% and name like %:name% and  brand like %:brand% ORDER BY code,brand,name")
    List<Product> findByKeyword(@Param("code") String code, @Param("name") String name,@Param("brand") String brand);


}

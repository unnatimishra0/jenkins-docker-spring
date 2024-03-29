package com.nagarro.training.service;

import com.nagarro.training.entity.Product;

import java.util.List;

public interface ProductService {

    List<Product> fetchByKeyword(String code, String name, String brand);

    Product fetchProductByCode(String productCode);

    long countProducts();

    void addProduct(Product product);
}

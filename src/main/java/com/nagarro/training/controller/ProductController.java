package com.nagarro.training.controller;


import com.nagarro.training.entity.Product;
import com.nagarro.training.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
public class ProductController {


    @Autowired
    private ProductService productService;

    @PostMapping(value = "/products")
    public ResponseEntity<Product> addProduct(@RequestBody Product product) {

        Product productObj = productService.fetchProductByCode(product.getCode());
        if (productObj == null) productService.addProduct(product);

        return ResponseEntity.status(HttpStatus.OK).body(productObj);
    }

    @GetMapping("/products")
    public List<Product> searchProduct(@RequestParam("code") String code, @RequestParam("name") String name, @RequestParam("brand") String brand) {
        List<Product> products = null;

        products = productService.fetchByKeyword(code, name, brand);

        return products;
    }

    @GetMapping("/products/{productCode}")
    public Product productDetails(@PathVariable("productCode") String productCode) {
        Product product = null;
        if (productCode != null) {
            product = productService.fetchProductByCode(productCode);
        }
        return product;
    }
}

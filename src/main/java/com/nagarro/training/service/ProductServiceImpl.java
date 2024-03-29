package com.nagarro.training.service;

import com.nagarro.training.entity.Product;
import com.nagarro.training.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class ProductServiceImpl implements ProductService {


    @Autowired
    private ProductRepository productRepository;

    @Autowired
    ReviewService reviewService;

    @Override
    public List<Product> fetchByKeyword(String code, String name, String brand) {

        List<Product> products = productRepository.findByKeyword(code, name, brand);
        for (Product product : products) {
            double avgRating = reviewService.findAverageRating(product.getCode());
            double totalReviews = reviewService.countReviews(product.getCode());
            product.setAverageRatings(avgRating);
            product.setReviews(totalReviews);
            this.addProduct(product);
        }
        return products;
    }

    @Override
    public Product fetchProductByCode(String productCode) {
        Product product = productRepository.findByCode(productCode);
        if (product != null) {
            double avgRating = reviewService.findAverageRating(product.getCode());
            double totalReviews = reviewService.countReviews(product.getCode());
            product.setAverageRatings(avgRating);
            product.setReviews(totalReviews);
            this.addProduct(product);
        }
        return product;
    }

    @Override
    public long countProducts() {
        return productRepository.count();
    }

    @Override
    public void addProduct(Product product) {
        productRepository.save(product);
    }
}

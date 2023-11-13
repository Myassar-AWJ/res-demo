package com.restaurant.restaurantdemo.service;


import com.restaurant.restaurantdemo.model.Product;
import com.restaurant.restaurantdemo.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.Optional;

public class ProductService {

    private final ProductRepository productRepository;
    private static final Logger logger = LoggerFactory.getLogger(ProductService.class);

    @Autowired
    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }


    public List<Product> getAllProducts() {
        try {
            return productRepository.findAll();
        } catch (Exception e) {
            // Log the exception
            logger.error("Error while retrieving all products", e);
            throw new RuntimeException("Error while retrieving all products", e);
        }
    }

//    public List<Product> getAllProductsByMenuId(Long menu_id) {
//        return productRepository.findAll();
//    }

    public Optional<Product> getProductById(Long id) {
        try {
        return productRepository.findById(id);
        } catch (Exception e) {
            // Log the exception
            logger.error("Error while retrieving product by id", e);
            throw new RuntimeException("Error while retrieving product by id", e);
        }
    }

    public Product createProduct(Product product) {
        try {
        return productRepository.save(product);
        } catch (Exception e) {
            // Log the exception
            logger.error("Error while creating product", e);
            throw new RuntimeException("Error while creating product", e);
        }
    }


    public void deleteProductById(Long id) {
        try {
            productRepository.deleteById(id);
        } catch (Exception e) {
            // Log the exception
            logger.error("Error while deleting product by ID", e);
            throw new RuntimeException("Error while deleting product by ID: " + id);
        }
    }
}

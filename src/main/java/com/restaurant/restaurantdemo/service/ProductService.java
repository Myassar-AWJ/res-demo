package com.restaurant.restaurantdemo.service;


import com.restaurant.restaurantdemo.model.Product;
import com.restaurant.restaurantdemo.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.List;
import java.util.Optional;

@Service
public class ProductService {

    private final ProductRepository productRepository;
    private final LoggerService logger;

    @Autowired
    public ProductService(ProductRepository productRepository,LoggerService logger) {
        this.productRepository = productRepository;
        this.logger=logger;
    }


    public List<Product> getAllProducts() {
        try {
            return productRepository.findAll();
        } catch (Exception e) {
            // Log the exception
            logger.error("Error while retrieving all products", e.getMessage());
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
            logger.error("Error while retrieving product by id", e.getMessage());
            throw new RuntimeException("Error while retrieving product by id", e);
        }
    }

    public Product createProduct(Product product) {
        try {
        return productRepository.save(product);
        } catch (Exception e) {
            // Log the exception
            logger.error("Error while creating product", e.getMessage());
            throw new RuntimeException("Error while creating product", e);
        }
    }


    public void deleteProductById(Long id) {
        try {
            productRepository.deleteById(id);
        } catch (Exception e) {
            // Log the exception
            logger.error("Error while deleting product by ID", e.getMessage());
            throw new RuntimeException("Error while deleting product by ID: " + id);
        }
    }
}

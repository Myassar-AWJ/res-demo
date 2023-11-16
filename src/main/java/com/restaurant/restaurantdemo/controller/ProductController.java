package com.restaurant.restaurantdemo.controller;

import com.restaurant.restaurantdemo.model.Product;
import com.restaurant.restaurantdemo.model.ResponseWithData;
import com.restaurant.restaurantdemo.service.LoggerService;
import com.restaurant.restaurantdemo.service.MenuService;
import com.restaurant.restaurantdemo.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collections;
import java.util.List;


@RestController
@RequestMapping("/api/products")
public class ProductController {

    private final ProductService productService;
    private final LoggerService logger;

//    private static final Logger logger = LoggerFactory.getLogger(MenuService.class);

    @Autowired
    public ProductController(ProductService productService, LoggerService logger) {
        this.productService = productService;
        this.logger = logger;
    }


    @GetMapping
    public ResponseEntity<ResponseWithData<List<Product>>> getAllProducts() {
        try {
            List<Product> products = productService.getAllProducts();
            ResponseWithData<List<Product>> response = new ResponseWithData<>("Success", products);
            return ResponseEntity.ok(response);

        } catch (Exception e) {
            // Log the exception
            logger.error("Error while getting all products", e.getMessage());
            ResponseWithData<List<Product>> errorResponse = new ResponseWithData<>(e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorResponse);

        }
    }

    public ResponseEntity<ResponseWithData<Product>> createProduct(Product product) {
        try {
            var newProduct = productService.createProduct(product);
            ResponseWithData<Product> response = new ResponseWithData<>("Success", newProduct);
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            logger.error("Error while create product cont", e.getMessage());
            ResponseWithData<Product> errorResponse = new ResponseWithData<>(e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorResponse);
        }
    }

    public ResponseEntity<ResponseWithData<Product>> updateProduct(long id, Product product) {
        try {
            var updatedProduct = productService.updateProduct(id, product);
            ResponseWithData<Product> response = new ResponseWithData<>("Success", updatedProduct);
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            logger.error("Error while updating product", e.getMessage());
            ResponseWithData<Product> errorResponse = new ResponseWithData<>(e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorResponse);
        }
    }

    public ResponseEntity<ResponseWithData<Void>> deleteProduct(Long id) {
        try {
            productService.deleteProductById(id);
            ResponseWithData<Void> response = new ResponseWithData<>("Success");
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            logger.error("Error while deleting product in cont", e.getMessage());
            ResponseWithData<Void> errorResponse = new ResponseWithData<>(e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorResponse);

        }
    }

}

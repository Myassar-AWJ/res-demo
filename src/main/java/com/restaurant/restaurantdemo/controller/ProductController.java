package com.restaurant.restaurantdemo.controller;

import com.restaurant.restaurantdemo.model.Product;
import com.restaurant.restaurantdemo.model.ResponseWithData;
import com.restaurant.restaurantdemo.service.MenuService;
import com.restaurant.restaurantdemo.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.util.Collections;
import java.util.List;


@RestController
@RequestMapping("/api/products")
public class ProductController {

    private final ProductService productService;

    private static final Logger logger = LoggerFactory.getLogger(MenuService.class);

    @Autowired
    public ProductController(ProductService productService){
        this.productService=productService;
    }


    @GetMapping
    public ResponseEntity<ResponseWithData<List<Product>>> getAllProducts() {
        try {
            List<Product> products = productService.getAllProducts();
            ResponseWithData<List<Product>> response = new ResponseWithData<>("Success", products);
            return ResponseEntity.ok(response);

         } catch (Exception e) {
            // Log the exception
            logger.error("Error while getting all products", e);
            ResponseWithData<List<Product>> errorResponse = new ResponseWithData<>("Error", Collections.emptyList());
            // Return a meaningful error response to the client
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorResponse);

        }
    }

}

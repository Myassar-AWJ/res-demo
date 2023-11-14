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
    public ProductController(ProductService productService,LoggerService logger){
        this.productService=productService;
        this.logger=logger;
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
            ResponseWithData<List<Product>> errorResponse = new ResponseWithData<>("Error", Collections.emptyList());
            // Return a meaningful error response to the client
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorResponse);

        }
    }

}

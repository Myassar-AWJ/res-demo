package com.restaurant.restaurantdemo.controller;

import com.restaurant.restaurantdemo.model.Product;
import com.restaurant.restaurantdemo.model.ResponseWithData;
import com.restaurant.restaurantdemo.service.LoggerService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


import com.restaurant.restaurantdemo.model.Restaurant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import com.restaurant.restaurantdemo.service.RestaurantService;
import org.springframework.http.ResponseEntity;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/restaurants")
public class RestaurantController {

    private final RestaurantService restaurantService;

    private final LoggerService logger;

    @Autowired
    public RestaurantController(RestaurantService restaurantService,LoggerService logger) {
        this.restaurantService = restaurantService;
        this.logger=logger;
    }

    @GetMapping
    public ResponseEntity<List<Restaurant>> getAllRestaurants() {
        try {
            List<Restaurant> restaurants = restaurantService.getAllRestaurants();

            if (restaurants.isEmpty()) {
                return ResponseEntity.status(HttpStatus.NO_CONTENT).body(restaurants);

            }
            // Return an empty list with a custom message

            return ResponseEntity.ok(restaurants);
        } catch (Exception e) {
            // Log the exception for debugging purposes
            logger.error("An error occurred while processing the request", e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);

        }
    }

    @GetMapping("/{RestaurantId}")
    public Optional<Restaurant> getRestaurantById(@PathVariable Long RestaurantId) {
        return restaurantService.getRestaurantById(RestaurantId);
    }

    @PostMapping
    public ResponseEntity<ResponseWithData<Restaurant>> createRestaurant(@RequestBody Restaurant Restaurant) {
        try {
           var restaurant= restaurantService.createRestaurant(Restaurant);
            ResponseWithData<Restaurant> response = new ResponseWithData<>("Success", restaurant);
            return ResponseEntity.ok(response);

        } catch (Exception e) {
            // Log the exception
            logger.error("Error while getting all products", e.getMessage());
            ResponseWithData<Restaurant> errorResponse = new ResponseWithData<>("Error", null);
            // Return a meaningful error response to the client
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorResponse);

        }

    }

    @PutMapping("/{RestaurantId}")
    public Restaurant updateRestaurant(@PathVariable Long RestaurantId, @RequestBody Restaurant RestaurantDetails) {
        return restaurantService.updateRestaurant(RestaurantId, RestaurantDetails);
    }

    @DeleteMapping("/{RestaurantId}")
    public void deleteRestaurant(@PathVariable Long RestaurantId) {
        restaurantService.deleteRestaurant(RestaurantId);
    }
}

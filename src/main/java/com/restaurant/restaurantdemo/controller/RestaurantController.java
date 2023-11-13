package com.restaurant.restaurantdemo.controller;

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
@RequestMapping("/api/restaurant")
public class RestaurantController {
    private static final Logger logger = LoggerFactory.getLogger(RestaurantController.class);

    private final RestaurantService restaurantService;

    @Autowired
    public RestaurantController(RestaurantService restaurantService) {
        this.restaurantService = restaurantService;
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
            logger.error("An error occurred while processing the request", e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);

        }
    }

    @GetMapping("/{RestaurantId}")
    public Optional<Restaurant> getRestaurantById(@PathVariable Long RestaurantId) {
        return restaurantService.getRestaurantById(RestaurantId);
    }

    @PostMapping
    public Restaurant createRestaurant(@RequestBody Restaurant Restaurant) {
        return restaurantService.createRestaurant(Restaurant);
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

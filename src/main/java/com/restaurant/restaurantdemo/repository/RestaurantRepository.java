package com.restaurant.restaurantdemo.repository;

import com.restaurant.restaurantdemo.model.Restaurant;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RestaurantRepository extends JpaRepository<Restaurant, Long> {
}

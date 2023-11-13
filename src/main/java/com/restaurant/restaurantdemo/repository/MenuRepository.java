package com.restaurant.restaurantdemo.repository;

import com.restaurant.restaurantdemo.model.Menu;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MenuRepository extends JpaRepository<Menu,Long> {
}

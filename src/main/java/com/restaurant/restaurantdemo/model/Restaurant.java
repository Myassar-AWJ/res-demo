package com.restaurant.restaurantdemo.model;


import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Data;


@Data
@Entity
public class Restaurant {
    @Id
    private Long id;
    private String name;
    private String address;
}

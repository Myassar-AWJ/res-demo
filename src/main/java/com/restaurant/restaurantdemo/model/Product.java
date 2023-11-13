package com.restaurant.restaurantdemo.model;


import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Data;

@Data
@Entity
public class Product {
    @Id
    private Long id;
    private String name;
    private String description;
    private Double price;
    private Integer plu;
    private String image;
}

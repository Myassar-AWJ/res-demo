package com.restaurant.restaurantdemo.model;


import jakarta.persistence.*;
import lombok.Data;

import java.util.HashSet;
import java.util.Set;

@Data
@Entity
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    private Long id;
    private String name;
    private String description;
    private Double price;
    private Integer plu;
    private String image;

    @ManyToMany(mappedBy = "products")
    private Set<Menu> menus = new HashSet<>();
}

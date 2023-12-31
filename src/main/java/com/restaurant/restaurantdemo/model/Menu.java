package com.restaurant.restaurantdemo.model;

import jakarta.persistence.*;
import lombok.Data;

import java.util.HashSet;
import java.util.Set;

@Data
@Entity
public class Menu {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    private  Long id;
    private  String name;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "menu_product",
            joinColumns = @JoinColumn(name = "menu_id"),
            inverseJoinColumns = @JoinColumn(name = "product_id")
    )

    private Set<Product> products = new HashSet<>();
}

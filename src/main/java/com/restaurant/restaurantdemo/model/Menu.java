package com.restaurant.restaurantdemo.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Data;

@Data
@Entity
public class Menu {
    @Id
    private  Long id;
    private  String name;
}

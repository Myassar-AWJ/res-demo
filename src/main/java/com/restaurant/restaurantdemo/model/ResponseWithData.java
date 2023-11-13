package com.restaurant.restaurantdemo.model;

public class ResponseWithData<T> {
    private String message;
    private T data;


    public ResponseWithData(String message, T data) {
        this.message = message;
        this.data = data;
    }
}

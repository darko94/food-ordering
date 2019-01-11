package com.foodordering.service;

import java.util.List;
import java.util.UUID;

import com.foodordering.entity.Restaurant;

public interface RestaurantService {
    List<Restaurant> getAllRestaurants();
    Restaurant getRestaurantById(UUID id);
}

package com.foodordering.service.impl;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.foodordering.entity.Restaurant;
import com.foodordering.repository.RestaurantRepository;
import com.foodordering.service.RestaurantService;

@Service
public class RestaurantServiceImpl implements RestaurantService {

	@Autowired
	private RestaurantRepository restaurantRepository;
	
	@Override
	public List<Restaurant> getAllRestaurants() {
		return restaurantRepository.findAll();
	}

	@Override
	public Restaurant getRestaurantById(UUID id) {
		return restaurantRepository.getOne(id);
	}

	@Override
	public Restaurant saveRestaurant(Restaurant restaurant) {
		return restaurantRepository.save(restaurant);
	}

	@Override
	public void deleteRestaurantById(String id) {
		restaurantRepository.deleteById(UUID.fromString(id));	
	}

}

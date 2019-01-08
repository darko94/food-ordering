package com.foodordering.service.impl;

import java.util.List;

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

}

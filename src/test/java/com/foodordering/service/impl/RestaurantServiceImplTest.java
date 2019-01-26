package com.foodordering.service.impl;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import com.foodordering.entity.Restaurant;
import com.foodordering.repository.RestaurantRepository;

@RunWith(MockitoJUnitRunner.class)
public class RestaurantServiceImplTest {

	@InjectMocks
	private RestaurantServiceImpl restaurantServiceImpl;

	@Mock
	private RestaurantRepository restaurantRepository;

	private List<Restaurant> restaurants;
	private Restaurant r1, r2, r3, r4;

	@Before
	public void setup() {
		r1 = new Restaurant();
		r1.setName("Test restaurant");

		restaurants = new ArrayList<>();
		restaurants.add(r1);
		restaurants.add(r2);
		restaurants.add(r3);
		restaurants.add(r4);
	}

	@Test
	public void testGetAllRestaurants_ShouldBeEqualsNotNullAndSameSize() {
		when(restaurantRepository.findAll()).thenReturn(restaurants);

		List<Restaurant> restaurantsTest = restaurantServiceImpl.getAllRestaurants();
		assertEquals(restaurants, restaurantsTest);
		assertNotNull(restaurantsTest);
		assertEquals(4, restaurantsTest.size());
	}

	@Test
	public void testGetAllRestaurants_ShouldBeNull() {
		when(restaurantRepository.findAll()).thenReturn(null);

		List<Restaurant> restaurantsTest = restaurantServiceImpl.getAllRestaurants();
		assertNull(restaurantsTest);
	}

	@Test
	public void testGetRestaurantById_ShouldBeEqualsAndNotNull() {
		when(restaurantRepository.getOne(Mockito.any(UUID.class))).thenReturn(r1);

		Restaurant restaurantTest = restaurantServiceImpl.getRestaurantById(UUID.randomUUID());
		assertEquals(r1, restaurantTest);
		assertNotNull(restaurantTest);
		assertEquals("Test restaurant", restaurantTest.getName());
	}

	@Test
	public void testGetRestaurantById_ShouldBeNull() {
		when(restaurantRepository.getOne(Mockito.any(UUID.class))).thenReturn(null);

		Restaurant restaurantTest = restaurantServiceImpl.getRestaurantById(UUID.randomUUID());
		assertNull(restaurantTest);
	}

	@Test
	public void testSaveRestaurant_ShouldReturnResult() {
		when(restaurantRepository.save(Mockito.any(Restaurant.class))).thenReturn(r1);

		Restaurant restaurantTest = restaurantServiceImpl.saveRestaurant(new Restaurant());
		assertNotNull(restaurantTest);
		assertEquals(r1, restaurantTest);
	}

	@Test
	public void testSaveRestaurant_ShouldReturnNull() {
		when(restaurantRepository.save(Mockito.any(Restaurant.class))).thenReturn(null);

		Restaurant restaurantTest = restaurantServiceImpl.saveRestaurant(new Restaurant());
		assertNull(restaurantTest);
	}
	
	@Test
	public void testDeleteRestaurantById() {
		doNothing().when(restaurantRepository).deleteById(Mockito.any(UUID.class));
		
		UUID uuid = UUID.randomUUID();
		restaurantServiceImpl.deleteRestaurantById(uuid.toString());
		
		verify(restaurantRepository, times(1)).deleteById(uuid);
	}
}

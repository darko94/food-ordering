package com.foodordering.service.impl;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import com.foodordering.entity.GroupOrder;
import com.foodordering.entity.Restaurant;
import com.foodordering.repository.GroupOrderRepository;
import com.foodordering.service.RestaurantService;

@RunWith(MockitoJUnitRunner.class)
public class GroupOrderServiceImplTest {

	@InjectMocks
	private GroupOrderServiceImpl groupOrderServiceImpl;

	@Mock
	private GroupOrderRepository groupOrderRepository;
	
	@Mock
	private RestaurantService restaurantService;

	private List<GroupOrder> groupOrders;
	private GroupOrder go1, go2, go3;
	private Restaurant restaurant;
	private UUID uuidForRestaurant;

	@Before
	public void setup() {
		uuidForRestaurant = UUID.randomUUID();
		
		go1 = new GroupOrder();
		go1.setCreator("Cre Ator");
		go1.setCreated(new Date());
		go1.setTimeout(10);
		go2 = new GroupOrder();
		go2.setCreated(new Date());
		go2.setTimeout(12);
		go3 = new GroupOrder();
		go3.setCreated(new Date());
		go3.setTimeout(2);
		
		groupOrders = new ArrayList<>();
		groupOrders.add(go1);
		groupOrders.add(go2);
		groupOrders.add(go3);
		
		restaurant = new Restaurant();
		restaurant.setGroupOrders(groupOrders);
		restaurant.setId(uuidForRestaurant);
	}

	@Test
	public void testGetAllGroupOrders_ShouldBeEqualsNotNullAndSameSize() {
		when(groupOrderRepository.findAll()).thenReturn(groupOrders);

		List<GroupOrder> groupOrdersTest = groupOrderServiceImpl.getAllGroupOrders();
		assertEquals(groupOrders, groupOrdersTest);
		assertNotNull(groupOrdersTest);
		assertEquals(3, groupOrdersTest.size());
	}

	@Test
	public void testGetAllGroupOrders_ShouldBeNull() {
		when(groupOrderRepository.findAll()).thenReturn(null);

		List<GroupOrder> groupOrdersTest = groupOrderServiceImpl.getAllGroupOrders();
		assertNull(groupOrdersTest);
	}

	@Test
	public void testGetGroupOrderById_ShouldBeEqualsAndNotNull() {
		when(groupOrderRepository.getOne(Mockito.any(UUID.class))).thenReturn(go1);

		GroupOrder groupOrderTest = groupOrderServiceImpl.getGroupOrderById(UUID.randomUUID());
		assertEquals(go1, groupOrderTest);
		assertNotNull(groupOrderTest);
		assertEquals("Cre Ator", groupOrderTest.getCreator());
	}

	@Test
	public void testGetGroupOrderById_ShouldBeNull() {
		when(groupOrderRepository.getOne(Mockito.any(UUID.class))).thenReturn(null);

		GroupOrder groupOrderTest = groupOrderServiceImpl.getGroupOrderById(UUID.randomUUID());
		assertNull(groupOrderTest);
	}

	@Test
	public void testSaveGroupOrder_ShouldReturnResult() {
		when(groupOrderRepository.save(Mockito.any(GroupOrder.class))).thenReturn(go1);

		GroupOrder groupOrderTest = groupOrderServiceImpl.saveGroupOrder(new GroupOrder());
		assertNotNull(groupOrderTest);
		assertEquals(go1, groupOrderTest);
	}

	@Test
	public void testSaveGroupOrder_ShouldReturnNull() {
		when(groupOrderRepository.save(Mockito.any(GroupOrder.class))).thenReturn(null);

		GroupOrder groupOrderTest = groupOrderServiceImpl.saveGroupOrder(new GroupOrder());
		assertNull(groupOrderTest);
	}
	
	@Test
	public void testDeleteGroupOrderById() {
		doNothing().when(groupOrderRepository).deleteById(Mockito.any(UUID.class));
		
		UUID uuid = UUID.randomUUID();
		groupOrderServiceImpl.deleteGroupOrderById(uuid.toString());
		
		verify(groupOrderRepository, times(1)).deleteById(uuid);
	}
	
	@Test
	public void testGetAllActiveGroupOrders_ShouldBeEqualsAndNotNull() {
		when(restaurantService.getRestaurantById(Mockito.any(UUID.class))).thenReturn(restaurant);
		
		List<GroupOrder> groupOrdersTest = groupOrderServiceImpl.getAllActiveGroupOrders(uuidForRestaurant);
		
		assertEquals(groupOrders, groupOrdersTest);
		assertNotNull(groupOrdersTest);
		assertEquals(3, groupOrdersTest.size());
	}
	
	@Test(expected = NullPointerException.class)
	public void testGetAllActiveGroupOrders_ShouldThrowException() {
		when(restaurantService.getRestaurantById(Mockito.any(UUID.class))).thenReturn(null);
		
		List<GroupOrder> groupOrdersTest = groupOrderServiceImpl.getAllActiveGroupOrders(uuidForRestaurant);
		
		assertNull(groupOrdersTest);
	}
}

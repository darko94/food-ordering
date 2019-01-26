package com.foodordering.service.impl;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
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

import com.foodordering.entity.GroupOrder;
import com.foodordering.entity.Order;
import com.foodordering.repository.OrderRepository;

@RunWith(MockitoJUnitRunner.class)
public class OrderServiceImplTest {

	@InjectMocks
	private OrderServiceImpl orderServiceImpl;
	
	@Mock
	private OrderRepository orderRepository;
	
	private Order order1, order2;
	private List<Order> orders;
	private GroupOrder groupOrder;
	
	@Before
	public void setup() {
		order1 = new Order();
		order1.setEmployeeName("Emp Loyee");
		order1.setPrice(253.0);
		order2 = new Order();
		order2.setPrice(246.5);
		
		groupOrder = new GroupOrder();
		UUID uuid = UUID.randomUUID();
		groupOrder.setId(uuid);
		
		order1.setGroupOrder(groupOrder);
		order2.setGroupOrder(groupOrder);
		
		orders = new ArrayList<>();
		orders.add(order1);
		orders.add(order2);
	}
	
	@Test
	public void testSaveOrder_ShouldReturnResult() {
		when(orderRepository.save(Mockito.any(Order.class))).thenReturn(order1);

		Order orderTest = orderServiceImpl.saveOrder(new Order());
		assertNotNull(orderTest);
		assertEquals(order1, orderTest);
	}

	@Test
	public void testSaveOrder_ShouldReturnNull() {
		when(orderRepository.save(Mockito.any(Order.class))).thenReturn(null);

		Order orderTest = orderServiceImpl.saveOrder(new Order());
		assertNull(orderTest);
	}
	
	@Test
	public void testDeleteOrderById() {
		doNothing().when(orderRepository).deleteById(Mockito.any(UUID.class));
		
		UUID uuid = UUID.randomUUID();
		orderServiceImpl.deleteOrderById(uuid);
		
		verify(orderRepository, times(1)).deleteById(uuid);
	}
	
	@Test
	public void testGetAllOrders_ShouldBeEqualsNotNullAndSameSize() {
		when(orderRepository.findAll()).thenReturn(orders);

		List<Order> ordersTest = orderServiceImpl.getAllOrders();
		assertEquals(orders, ordersTest);
		assertNotNull(ordersTest);
		assertEquals(2, ordersTest.size());
	}

	@Test
	public void testGetAllOrders_ShouldBeNull() {
		when(orderRepository.findAll()).thenReturn(null);

		List<Order> ordersTest = orderServiceImpl.getAllOrders();
		assertNull(ordersTest);
	}
	
	@Test
	public void testFindOne_ShouldBeEqualsAndNotNull() {
		when(orderRepository.getOne(Mockito.any(UUID.class))).thenReturn(order1);

		Order orderTest = orderServiceImpl.findOne(UUID.randomUUID());
		assertEquals(order1, orderTest);
		assertNotNull(orderTest);
		assertEquals("Emp Loyee", orderTest.getEmployeeName());
	}

	@Test
	public void testFindOne_ShouldBeNull() {
		when(orderRepository.getOne(Mockito.any(UUID.class))).thenReturn(null);

		Order orderTest = orderServiceImpl.findOne(UUID.randomUUID());
		assertNull(orderTest);
	}
	
	@Test
	public void testGetSumAllByGroupOrder_ShouldBeEquals() {
		when(orderRepository.findAll()).thenReturn(orders);
		
		double testResult = orderServiceImpl.getSumAllByGroupOrder(groupOrder);
		assertEquals(499.5, testResult, 0.0001);
	}
	
	@Test
	public void testGetSumAllByGroupOrder_ShouldNotBeEquals() {
		when(orderRepository.findAll()).thenReturn(orders);
		
		double testResult = orderServiceImpl.getSumAllByGroupOrder(new GroupOrder());
		assertNotEquals(200, testResult, 0.0001);
	}
	
	@Test(expected = NullPointerException.class)
	public void testGetSumAllByGroupOrder_ShouldThrowException() {
		when(orderRepository.findAll()).thenReturn(null);
		
		double testResult = orderServiceImpl.getSumAllByGroupOrder(groupOrder);
		assertEquals(499.5, testResult, 0.0001);
	}
	
	@Test
	public void testGetSumAllByGroupOrder_ShoulBeZero() {
		when(orderRepository.findAll()).thenReturn(orders);
		
		double testResult = orderServiceImpl.getSumAllByGroupOrder(new GroupOrder());
		assertEquals(0, testResult, 0.0001);
	}
	
	@Test
	public void testGetAllOrdersForGroupOrder_ShouldBeEqualsNotNullAndSameSize() {
		when(orderRepository.findAll()).thenReturn(orders);
		
		List<Order> ordersTest = orderServiceImpl.getAllOrdersForGroupOrder(groupOrder);
		assertEquals(orders, ordersTest);
		assertNotNull(ordersTest);
		assertEquals(2, ordersTest.size());
	}
	
	@Test(expected = NullPointerException.class)
	public void testGetAllOrdersForGroupORder_ShouldThrowException() {
		when(orderRepository.findAll()).thenReturn(orders);
		
		List<Order> ordersTest = orderServiceImpl.getAllOrdersForGroupOrder(new GroupOrder());
		assertNull(ordersTest);
	}
}
package com.foodordering.service.impl;

import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessagePreparator;

import com.foodordering.entity.GroupOrder;
import com.foodordering.entity.Order;
import com.foodordering.entity.Restaurant;
import com.foodordering.service.OrderService;

@RunWith(MockitoJUnitRunner.class)
public class EmailServiceImplTest {

	@InjectMocks
	private EmailServiceImpl emailServiceImpl;
	
	@Mock
	private JavaMailSender mailSender;
	
	@Mock
	private OrderService orderService;
	
	private GroupOrder groupOrder;
	private Restaurant restaurant;
	private List<Order> orders;
	private Order o1, o2;
	
	@Before
	public void setup() {
		restaurant = new Restaurant();
		restaurant.setEmail("test@test.com");
		groupOrder = new GroupOrder();
		groupOrder.setRestaurant(restaurant);
		o1 = new Order();
		o2 = new Order();
		orders = new ArrayList<>();
		orders.add(o1);
		orders.add(o2);
	}
	
	@Test
	public void testSendMail() {
		doNothing().when(mailSender).send(Mockito.any(MimeMessagePreparator.class));
		
		emailServiceImpl.sendMail(groupOrder);
		emailServiceImpl.sendMail(groupOrder);
		verify(mailSender, times(2)).send(Mockito.any(MimeMessagePreparator.class));
	}
	
	@Test
	public void testEmailTimer() {
		when(orderService.getAllOrdersForGroupOrder(Mockito.any(GroupOrder.class))).thenReturn(orders);
		doNothing().when(mailSender).send(Mockito.any(MimeMessagePreparator.class));
		
		emailServiceImpl.emailTimer(0, groupOrder);
		verify(mailSender, times(0)).send(Mockito.any(MimeMessagePreparator.class));
	}
}

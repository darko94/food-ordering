package com.foodordering.service;

import java.util.List;
import java.util.UUID;

import com.foodordering.entity.GroupOrder;
import com.foodordering.entity.Order;

public interface OrderService {
	public Order saveOrder(Order order);
    public void deleteOrderById(UUID id);
    List<Order> getAllOrders();
    public Order findOne(UUID id);
   
   
    double getSumAllByGroupOrder(GroupOrder groupOrder);
 
}

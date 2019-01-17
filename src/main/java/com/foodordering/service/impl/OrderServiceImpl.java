package com.foodordering.service.impl;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.foodordering.entity.GroupOrder;
import com.foodordering.entity.Order;
import com.foodordering.repository.OrderRepository;
import com.foodordering.service.OrderService;

@Service
public class OrderServiceImpl implements OrderService {

	@Autowired
	private OrderRepository orderRepository;
	
	@Override
	public Order saveOrder(Order order) {
		return orderRepository.save(order);
	}

	@Override
	public void deleteOrderById(UUID id) {
		orderRepository.deleteById(id);
	}

	@Override
	public List<Order> getAllOrders() {
		return orderRepository.findAll();
	}

	@Override
	public Order findOne(UUID id) {
		return orderRepository.getOne(id);
	}

	@Override
	public double getSumAllByGroupOrder(GroupOrder groupOrder) {
		double total = 0;
        List<Order> orderList = orderRepository.findAll();

        for (Order o : orderList){
            if(o.getGroupOrder().getId() == (groupOrder.getId())){
                total += o.getPrice();
            }

        }
        return total;
	}

}

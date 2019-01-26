package com.foodordering.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.foodordering.entity.GroupOrder;
import com.foodordering.entity.Restaurant;
import com.foodordering.repository.GroupOrderRepository;
import com.foodordering.service.GroupOrderService;
import com.foodordering.service.RestaurantService;

@Service
public class GroupOrderServiceImpl implements GroupOrderService {

	@Autowired
	private GroupOrderRepository groupOrderRepository;

	@Autowired
	private RestaurantService restaurantService;

	@Override
	public List<GroupOrder> getAllGroupOrders() {
		return groupOrderRepository.findAll();
	}

	@Override
	public GroupOrder getGroupOrderById(UUID id) {
		return groupOrderRepository.getOne(id);
	}

	@Override
	public GroupOrder saveGroupOrder(GroupOrder groupOrder) {
		return groupOrderRepository.save(groupOrder);
	}

	@Override
	public void deleteGroupOrderById(String id) {
		groupOrderRepository.deleteById(UUID.fromString(id));
	}

	@Override
	public List<GroupOrder> getAllActiveGroupOrders(UUID id) {
		Restaurant r = restaurantService.getRestaurantById(id);
		List<GroupOrder> groupOrders = r.getGroupOrders();
		List<GroupOrder> activeGroupOrders = new ArrayList<>();
		for (GroupOrder order : groupOrders) {
			long countDownDate = order.getCreated().getTime() + 60000 * order.getTimeout();
			long now = System.currentTimeMillis();
			long distance = countDownDate - now;
			if (distance > 0) {
				activeGroupOrders.add(order);
			}
		}

		return activeGroupOrders;
	}

}

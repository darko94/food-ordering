package com.foodordering.service;

import java.util.List;
import java.util.UUID;

import com.foodordering.entity.GroupOrder;

public interface GroupOrderService {
	List<GroupOrder> getAllGroupOrders();

	GroupOrder getGroupOrderById(UUID id);

	GroupOrder saveGroupOrder(GroupOrder groupOrder);

	void deleteGroupOrderById(String id);

	List<GroupOrder> getAllActiveGroupOrders(UUID id);
}

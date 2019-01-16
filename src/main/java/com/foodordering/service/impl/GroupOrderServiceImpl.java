package com.foodordering.service.impl;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.foodordering.entity.GroupOrder;
import com.foodordering.repository.GroupOrderRepository;
import com.foodordering.service.GroupOrderService;

@Service
public class GroupOrderServiceImpl implements GroupOrderService {

	@Autowired
	private GroupOrderRepository groupOrderRepository;
	
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

}

package com.foodordering.service;

import com.foodordering.entity.GroupOrder;

public interface EmailService {
	void sendMail(GroupOrder groupOrder);

	void emailTimer(long delay, GroupOrder groupOrder);
}

package com.foodordering.dto;

import javax.validation.constraints.NotNull;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Date;
import java.util.List;

@EqualsAndHashCode(callSuper = false)
public @Data class GroupOrderDTO extends AbstractDto {

	@NotNull
	private int timeout;
	private Date created;
	@NotNull(message = "Please fill creator... Must not be null !")
	private String creator;
	private List<OrderDTO> orders;
	private RestaurantDTO restaurant;
	private String restaurantIdString;

	public double getTotal() {
		double total = 0;
		for (OrderDTO order : getOrders()) {
			total += order.getPrice();
		}
		return total;
	}
}

package com.foodordering.dto;

import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = false)
public @Data class OrderDTO extends AbstractDto {

	@NotNull(message = "Please fill employee name... Must not be null !")
	@Size(min = 1, max = 1000)
	private String employeeName;
	@NotNull(message = "Please fill item name... Must not be null !")
	@Size(min = 1, max = 1000)
	private String itemName;
	@NotNull(message = "Please fill price... Must not be null !")
	@DecimalMin(value = "0.1", inclusive = true, message = "Must be greater than or equal to 0.1!")
	private double price;
	private GroupOrderDTO groupOrder;
	private String groupOrderIdString;
}

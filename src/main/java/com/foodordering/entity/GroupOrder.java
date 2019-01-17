package com.foodordering.entity;

import java.util.Date;
import java.util.List;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Entity
@Table(name = "group_orders")
@EqualsAndHashCode(callSuper = false)
public @Data class GroupOrder extends AbstractEntity {

	private static final long serialVersionUID = 1L;
	@Basic(optional = false)
	@Column
	private int timeout;
	@Column
	private Date created;
	@Basic(optional = false)
	@Column
	private String creator;
	@OneToMany(mappedBy = "groupOrder")
	private List<Order> orders;
	@JoinColumn(name = "restaurant_id", referencedColumnName = "id")
	@ManyToOne
	private Restaurant restaurant;

	public double getTotal() {
		double total = 0;
		for (Order order : getOrders()) {
			total += order.getPrice();
		}
		return total;
	}
}

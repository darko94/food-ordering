package com.foodordering.entity;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.*;

@Entity
@Table(name = "group_orders")
public class GroupOrder implements Serializable {

	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(generator = "UUID")
	@GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
	@Column(name = "id", updatable = false, nullable = false)
	private UUID id;
	@Basic(optional = false)
	@Column(name = "timeout")
	private int timeout;
	@Column(name = "created")
	private Date created;
	@Basic(optional = false)
	@Column(name = "creator")
	private String creator;
	@OneToMany(mappedBy = "groupOrderId")
	private List<Order> orderList;
	@JoinColumn(name = "restaurant_id", referencedColumnName = "id")
	@ManyToOne
	private Restaurant restaurantId;

	public GroupOrder() {
	}

	public GroupOrder(UUID id) {
		this.id = id;
	}

	public GroupOrder(GroupOrder groupOrder) {
		this.id = groupOrder.id;
		this.timeout = groupOrder.timeout;
		this.created = groupOrder.created;
		this.creator = groupOrder.creator;
		this.restaurantId = groupOrder.restaurantId;
	}

	public GroupOrder(UUID id, int timeout, String creator) {
		this.id = id;
		this.timeout = timeout;
		this.creator = creator;
	}

	public GroupOrder(@NotNull int timeout, Date created, @NotNull @Size(min = 1, max = 1000) String creator) {
		this.timeout = timeout;
		this.created = created;
		this.creator = creator;
	}

	public UUID getId() {
		return id;
	}

	public void setId(UUID id) {
		this.id = id;
	}

	public int getTimeout() {
		return timeout;
	}

	public void setTimeout(int timeout) {
		this.timeout = timeout;
	}

	public Date getCreated() {
		return created;
	}

	public void setCreated(Date created) {
		this.created = created;
	}

	public String getCreator() {
		return creator;
	}

	public void setCreator(String creator) {
		this.creator = creator;
	}

	public List<Order> getOrderList() {
		return orderList;
	}

	public void setOrderList(List<Order> orderList) {
		this.orderList = orderList;
	}

	public Restaurant getRestaurantId() {
		return restaurantId;
	}

	public void setRestaurantId(Restaurant restaurantId) {
		this.restaurantId = restaurantId;
	}

	public double getTotal() {
		double total = 0;
		for (Order order : getOrderList()) {
			total += order.getPrice();
		}
		return total;
	}

	@Override
	public String toString() {
		return "GroupOrder [id=" + id + ", timeout=" + timeout + ", created=" + created + ", creator=" + creator
				+ ", orderList=" + orderList + ", restaurantId=" + restaurantId + "]";
	}

}

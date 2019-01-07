package com.foodordering.entity;

import javax.persistence.*;

import org.hibernate.annotations.GenericGenerator;

import java.io.Serializable;
import java.util.UUID;

@Entity
@Table(name = "orders")
public class Order implements Serializable {

	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(generator = "UUID")
	@GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
	@Column(name = "id", updatable = false, nullable = false)
	private UUID id;
	@Basic(optional = false)
	@Column(name = "employee_name")
	private String employeeName;
	@Basic(optional = false)
	@Column(name = "item_name")
	private String itemName;
	@Basic(optional = false)
	@Column(name = "price")
	private double price;
	@JoinColumn(name = "group_order_id", referencedColumnName = "id")
	@ManyToOne
	private GroupOrder groupOrderId;

	public Order() {
	}

	public Order(UUID id) {
		this.id = id;
	}

	public Order(UUID id, String employeeName, String itemName, double price) {
		this.id = id;
		this.employeeName = employeeName;
		this.itemName = itemName;
		this.price = price;
	}

	public UUID getId() {
		return id;
	}

	public void setId(UUID id) {
		this.id = id;
	}

	public String getEmployeeName() {
		return employeeName;
	}

	public void setEmployeeName(String employeeName) {
		this.employeeName = employeeName;
	}

	public String getItemName() {
		return itemName;
	}

	public void setItemName(String itemName) {
		this.itemName = itemName;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public GroupOrder getGroupOrderId() {
		return groupOrderId;
	}

	public void setGroupOrderId(GroupOrder groupOrderId) {
		this.groupOrderId = groupOrderId;
	}

	@Override
	public String toString() {
		return "Order [id=" + id + ", employeeName=" + employeeName + ", itemName=" + itemName + ", price=" + price
				+ ", groupOrderId=" + groupOrderId + "]";
	}

}
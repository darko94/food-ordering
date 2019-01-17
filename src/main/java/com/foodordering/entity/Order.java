package com.foodordering.entity;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Entity
@Table(name = "orders")
@EqualsAndHashCode(callSuper = false)
public @Data class Order extends AbstractEntity {

	private static final long serialVersionUID = 1L;
	@Basic(optional = false)
	@Column
	private String employeeName;
	@Basic(optional = false)
	@Column
	private String itemName;
	@Basic(optional = false)
	@Column
	private double price;
	@JoinColumn(name = "group_order_id", referencedColumnName = "id")
	@ManyToOne
	private GroupOrder groupOrder;
}
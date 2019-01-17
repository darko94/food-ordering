package com.foodordering.entity;

import java.util.List;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Entity
@Table(name = "restaurants")
@EqualsAndHashCode(callSuper = false)
public @Data class Restaurant extends AbstractEntity {

	private static final long serialVersionUID = 1L;
	@Column
	private String email;
	@Basic(optional = false)
	@Column
	private String name;
	@Basic(optional = false)
	@Column
	private String address;
	@Basic(optional = false)
	@Column
	private String phoneNumber;
	@Basic(optional = false)
	@Column
	private String menuUrl;
	@Column
	private String deliveryTime;
	@Column
	private String additionalCharges;
	@OneToMany(mappedBy = "restaurant")
	private List<GroupOrder> groupOrders;
}
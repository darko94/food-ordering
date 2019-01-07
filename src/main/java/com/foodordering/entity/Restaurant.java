package com.foodordering.entity;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "restaurants")
public class Restaurant implements Serializable {

	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(generator = "UUID")
	@GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
	@Column(name = "id", updatable = false, nullable = false)
	private UUID id;
	@Column(name = "email")
	private String email;
	@Basic(optional = false)
	@Column(name = "name")
	private String name;
	@Basic(optional = false)
	@Column(name = "address")
	private String address;
	@Basic(optional = false)
	@Column(name = "phone_number")
	private String phoneNumber;
	@Basic(optional = false)
	@Column(name = "menu_url")
	private String menuUrl;
	@Column(name = "delivery_time")
	private String deliveryTime;
	@Column(name = "additional_charges")
	private String additionalCharges;
	@OneToMany(mappedBy = "restaurantId")
	private List<GroupOrder> groupOrderList;

	public Restaurant() {
	}

	public Restaurant(UUID id) {
		this.id = id;
	}

	public Restaurant(UUID id, String email, String name, String address, String phoneNumber, String menuUrl) {
		this.id = id;
		this.email = email;
		this.name = name;
		this.address = address;
		this.phoneNumber = phoneNumber;
		this.menuUrl = menuUrl;
	}

	public UUID getId() {
		return id;
	}

	public void setId(UUID id) {
		this.id = id;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public String getMenuUrl() {
		return menuUrl;
	}

	public void setMenuUrl(String menuUrl) {
		this.menuUrl = menuUrl;
	}

	public String getDeliveryTime() {
		return deliveryTime;
	}

	public void setDeliveryTime(String deliveryTime) {
		this.deliveryTime = deliveryTime;
	}

	public String getAdditionalCharges() {
		return additionalCharges;
	}

	public void setAdditionalCharges(String additionalCharges) {
		this.additionalCharges = additionalCharges;
	}

	public List<GroupOrder> getGroupOrderList() {
		return groupOrderList;
	}

	public void setGroupOrderList(List<GroupOrder> groupOrderList) {
		this.groupOrderList = groupOrderList;
	}

	@Override
	public String toString() {
		return "Restaurant [id=" + id + ", email=" + email + ", name=" + name + ", address=" + address
				+ ", phoneNumber=" + phoneNumber + ", menuUrl=" + menuUrl + ", deliveryTime=" + deliveryTime
				+ ", additionalCharges=" + additionalCharges + "]";
	}

}
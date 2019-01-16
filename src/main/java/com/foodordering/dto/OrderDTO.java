package com.foodordering.dto;

import java.util.UUID;

import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class OrderDTO {
    private UUID id;
    @NotNull(message = "Please fill employee name... Must not be null !")
    @Size(min = 1, max = 1000)
    private String employeeName;
    @NotNull(message = "Please fill item name... Must not be null !")
    @Size(min = 1, max = 1000)
    private String itemName;
    @NotNull(message = "Please fill price... Must not be null !")
    @DecimalMin(value = "0.1", inclusive = true, message = "Must be greater than or equal to 0.1!")
    private double price;
    private GroupOrderDTO groupOrderId;
    private String groupOrderIdString;

    public OrderDTO() {
    }

    public OrderDTO(UUID id, String employeeName, String itemName, double price, GroupOrderDTO groupOrderId, String groupOrderIdString) {
        this.id = id;
        this.employeeName = employeeName;
        this.itemName = itemName;
        this.price = price;
        this.groupOrderId = groupOrderId;
        this.groupOrderIdString = groupOrderIdString;
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

    public String getGroupOrderIdString() {
        return groupOrderIdString;
    }

    public void setGroupOrderIdString(String groupOrderIdString) {
        this.groupOrderIdString = groupOrderIdString;
    }

    public GroupOrderDTO getGroupOrderId() {
        return groupOrderId;
    }

    public void setGroupOrderId(GroupOrderDTO groupOrderId) {
        this.groupOrderId = groupOrderId;
    }

}

package com.foodordering.dto;

import javax.validation.constraints.NotNull;
import java.util.Date;
import java.util.List;
import java.util.UUID;

public class GroupOrderDTO {
    private UUID id;
    @NotNull
    private int timeout;
    private Date created;
    @NotNull(message = "Please fill creator... Must not be null !")
    private String creator;
    private List<OrderDTO> orderList;
    private RestaurantDTO restaurantId;
    private String restaurantIdString;

    public GroupOrderDTO() {
    }

    public GroupOrderDTO(UUID id, int timeout, Date created, String creator, List<OrderDTO> orderList, RestaurantDTO restaurantId, int restaurantIdString) {
        this.id = id;
        this.timeout = timeout;
        this.created = created;
        this.creator = creator;
        this.orderList = orderList;
        this.restaurantId = restaurantId;
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

    public List<OrderDTO> getOrderList() {
        return orderList;
    }

    public void setOrderList(List<OrderDTO> orderList) {
        this.orderList = orderList;
    }

    public RestaurantDTO getRestaurantId() {
        return restaurantId;
    }

    public void setRestaurantId(RestaurantDTO restaurantId) {
        this.restaurantId = restaurantId;
    }

    public String getRestaurantIdString() {
        return restaurantIdString;
    }

    public void setRestaurantIdString(String restaurantIdString) {
        this.restaurantIdString = restaurantIdString;
    }

    public double getTotal() {
        double total = 0;
        for(OrderDTO order : getOrderList()) {
            total += order.getPrice();
        }
        return total;
    }

}

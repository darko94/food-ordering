package com.foodordering.dto;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

@EqualsAndHashCode(callSuper=false)
public @Data class RestaurantDTO extends AbstractDto {

    @NotNull(message = "Please fill email... Must not be null !")
    @Size(min = 1, max = 50)
    private String email;
    @NotNull(message = "Please fill name... Must not be null !")
    @Size(min = 1, max = 50)
    private String name;
    @NotNull(message = "Please fill address... Must not be null !")
    @Size(min = 1, max = 1000)
    private String address;
    @NotNull(message = "Please fill phone number... Must not be null !")
    @Size(min = 1, max = 1000)
    private String phoneNumber;
    private String menuUrl;
    @Size(max = 1000)
    private String deliveryTime;
    @Size(max = 1000)
    private String additionalCharges;
    private List<GroupOrderDTO> groupOrders;
}

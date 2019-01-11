package com.foodordering.dto;

import org.springframework.web.multipart.MultipartFile;

import javax.persistence.Transient;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;
import java.util.UUID;

public class RestaurantDTO {

    private UUID id;
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

    private List<GroupOrderDTO> groupOrderList;

    @NotNull
    @Transient
    private MultipartFile uploadFile;

    public RestaurantDTO() {
    }

    public RestaurantDTO(UUID id, String email, String name, String address, String phoneNumber, String menuUrl, String deliveryTime, String additionalCharges, List<GroupOrderDTO> groupOrderList) {
        this.id = id;
        this.email = email;
        this.name = name;
        this.address = address;
        this.phoneNumber = phoneNumber;
        this.menuUrl = menuUrl;
        this.deliveryTime = deliveryTime;
        this.additionalCharges = additionalCharges;
        this.groupOrderList = groupOrderList;
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

    public List<GroupOrderDTO> getGroupOrderList() {
        return groupOrderList;
    }

    public void setGroupOrderList(List<GroupOrderDTO> groupOrderList) {
        this.groupOrderList = groupOrderList;
    }

    public MultipartFile getUploadFile() {
        return uploadFile;
    }

    public void setUploadFile(MultipartFile uploadFile) {
        this.uploadFile = uploadFile;
    }
}

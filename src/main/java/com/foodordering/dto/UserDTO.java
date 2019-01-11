package com.foodordering.dto;

import java.util.UUID;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import com.foodordering.entity.Authority;

public class UserDTO {

    private UUID id;
    @NotNull(message = "Email must not be null")
    @Size(min = 1, max = 50)
    @Pattern(regexp="[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*@(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?", message="Invalid email")
    private String email;
    @NotNull (message = "Password must not be null")
    private String password;
    @NotNull(message = "Password must not be null")
    private String confirmPassword;
    private Authority authorityId;

    public UserDTO() {
    }

    public UserDTO(UUID id, String email, String password, String confirmPassword, Authority authorityId) {
        this.id = id;
        this.email = email;
        this.password = password;
        this.confirmPassword = confirmPassword;
        this.authorityId = authorityId;
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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getConfirmPassword() {
        return confirmPassword;
    }

    public void setConfirmPassword(String confirmPassword) {
        this.confirmPassword = confirmPassword;
    }

    public Authority getAuthorityId() {
        return authorityId;
    }

    public void setAuthorityId(Authority authorityId) {
        this.authorityId = authorityId;
    }
}

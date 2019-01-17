package com.foodordering.dto;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import com.foodordering.entity.Authority;

import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper=false)
public @Data class UserDTO extends AbstractDto {

    @NotNull(message = "Email must not be null")
    @Size(min = 1, max = 50)
    @Pattern(regexp="[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*@(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?", message="Invalid email")
    private String email;
    @NotNull (message = "Password must not be null")
    private String password;
    @NotNull(message = "Password must not be null")
    private String confirmPassword;
    private Authority authority;
}

package com.restapi.request;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@Getter
@Setter
public class LoginRequest {

    @NotEmpty
    @Size(min = 2, message = "UserName Field should not be empty")
    private String username;

    @NotEmpty
    @Size(min = 2, message = "Password Field should not be empty")
    private String password;
}

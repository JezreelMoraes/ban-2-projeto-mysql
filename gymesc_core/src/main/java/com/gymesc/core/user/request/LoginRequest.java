package com.gymesc.core.user.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;

@Getter
public class LoginRequest {

    @NotBlank @Email
    private String email;

    @NotBlank
    private String password;

}

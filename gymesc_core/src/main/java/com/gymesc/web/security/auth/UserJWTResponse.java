package com.gymesc.web.security.auth;

import java.io.Serializable;

import com.gymesc.core.user.repository.dto.UserDTO;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class UserJWTResponse implements Serializable {
    private static final long serialVersionUID = 1L;

    private String jwt;
    private UserDTO userDTO;

    public UserJWTResponse(String jwt, UserDTO userDTO) {
        this.jwt = jwt;
        this.userDTO = userDTO;
    }

}

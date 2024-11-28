package com.gymesc.core.user.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.gymesc.core.user.request.CreateUserRequest;
import com.gymesc.core.user.request.LoginRequest;
import com.gymesc.core.user.service.UserService;
import com.gymesc.core.user.service.UserServiceImpl;
import com.gymesc.infrastructure.exception.BusinessException;
import com.gymesc.web.security.auth.UserJWTResponse;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/public/user")
public class PublicUserController {

    private UserService userService;

    @PostMapping(value = "/login")
    public ResponseEntity<UserJWTResponse> login(@Valid @RequestBody LoginRequest loginRequest) throws BusinessException {
        UserJWTResponse userJWTResponse = this.userService.doLogin(loginRequest);

        return new ResponseEntity<>(userJWTResponse, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Void> createUser(@Valid @RequestBody CreateUserRequest createUserRequest) throws BusinessException {
        this.userService.createUser(createUserRequest);

        return new ResponseEntity<>(HttpStatus.OK);
    }

    @Autowired
    private void setUserService(UserServiceImpl userService) {
        this.userService = userService;
    }
}
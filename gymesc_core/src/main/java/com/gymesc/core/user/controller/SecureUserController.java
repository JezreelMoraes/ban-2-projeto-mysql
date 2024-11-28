package com.gymesc.core.user.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.gymesc.core.user.request.UpdateUserRequest;
import com.gymesc.web.security.auth.UserHelper;
import com.gymesc.core.user.repository.dto.UserDTO;
import com.gymesc.core.user.service.UserService;
import com.gymesc.infrastructure.exception.BusinessException;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/secure/user")
public class SecureUserController {

    private UserService userService;

    @GetMapping
    public ResponseEntity<UserDTO> loadLoggedUser() {
        UserDTO userDTO = this.userService.findActiveUserDTOById(UserHelper.getUserId());

        if (userDTO == null) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(userDTO);
    }

    @PutMapping
    public ResponseEntity<UserDTO> updateUser(@Valid @RequestBody UpdateUserRequest updateUserRequest) throws BusinessException {
        UserDTO updatedUserDTO = this.userService.updateUser(updateUserRequest);
        return ResponseEntity.ok(updatedUserDTO);
    }

    @DeleteMapping
    public ResponseEntity<Void> deleteUser() {
        this.userService.deleteUser(UserHelper.getUserId());
        return ResponseEntity.ok().build();
    }

    @Autowired
    private void setUserService(UserService userService) {
        this.userService = userService;
    }
}

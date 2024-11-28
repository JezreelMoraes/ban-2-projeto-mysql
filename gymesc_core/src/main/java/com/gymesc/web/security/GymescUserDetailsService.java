package com.gymesc.web.security;

import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.gymesc.core.user.repository.User;
import com.gymesc.core.user.service.UserService;
import com.gymesc.infrastructure.jwt.JWTCore;
import com.gymesc.web.security.auth.GymescUserDetails;
import com.gymesc.web.security.auth.UserBearerToken;

@Service
public class GymescUserDetailsService implements UserDetailsService {

    Logger logger = Logger.getLogger(GymescUserDetailsService.class.getName());

    private UserService userService;

    @Override
    public UserDetails loadUserByUsername(String authentication) throws UsernameNotFoundException {

        try {
            if (authentication.startsWith(JWTCore.USER_BEARER_IDENTIFICATION)) {
                return this.loadUserByUserBearerToken(authentication);
            }
        } catch (Exception e) {
            logger.info("Falha na autenticação JWT - " + e.getMessage());
            throw new UsernameNotFoundException("Bearer inválido: " + authentication);
        }

        throw new UsernameNotFoundException("Bearer Token JWT não localizado");
    }

    private UserDetails loadUserByUserBearerToken(String authentication) {
        String jwt = authentication.substring(JWTCore.USER_BEARER_IDENTIFICATION.length());
        UserBearerToken userBearerToken = JWTCore.parseJWTToUserBearerToken(jwt, true);

        if (userBearerToken == null) {
            logger.info("Bearer Token JWT inválido");
            throw new UsernameNotFoundException("Token inválido: " + authentication);
        }

        User user = userService.findActiveUserById(userBearerToken.getId());
        if (user == null || user.isDeleted()) {
            logger.info("Login de usuário inexistente: " + userBearerToken.getId());
            throw new UsernameNotFoundException("Usuário inválido: " + authentication);
        }

        return new GymescUserDetails(userBearerToken.getId(), userBearerToken.getEmail(), true, null);
    }

    @Autowired
    private void setUserService(UserService userService) {
        this.userService = userService;
    }
}
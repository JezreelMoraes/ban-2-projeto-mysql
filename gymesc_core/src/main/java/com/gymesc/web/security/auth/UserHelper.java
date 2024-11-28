package com.gymesc.web.security.auth;

import org.springframework.security.core.context.SecurityContextHolder;

public class UserHelper {

    public static Long getUserId() {
        GymescUserDetails seredUserDetails = getSeredUserDetails();
        if (seredUserDetails != null) {
            return seredUserDetails.getId();
        }

        return null;
    }

    public static String getUserEmail() {
        GymescUserDetails seredUserDetails = getSeredUserDetails();
        if (seredUserDetails != null) {
            return seredUserDetails.getUsername();
        }

        return null;
    }

    public static boolean isLoggedIn() {
        GymescUserDetails seredUserDetails = getSeredUserDetails();
        if (seredUserDetails != null) {
            return seredUserDetails.isEnabled();
        }

        return false;
    }

    public static GymescUserDetails getSeredUserDetails() {

        if (SecurityContextHolder.getContext().getAuthentication() != null && SecurityContextHolder.getContext().getAuthentication().getPrincipal() instanceof GymescUserDetails) {
            return (GymescUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        }

        return null;
    }
}
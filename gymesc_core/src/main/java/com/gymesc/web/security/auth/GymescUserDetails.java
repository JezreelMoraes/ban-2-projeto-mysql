package com.gymesc.web.security.auth;


import com.gymesc.infrastructure.GymEscConstants;
import org.apache.commons.lang3.StringUtils;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class GymescUserDetails implements UserDetails {
    private final Long id;
    private final String email;
    private final boolean enabled;
    String roles;

    protected List<GrantedAuthority> grantedAuthorityList = new ArrayList<>();

    public GymescUserDetails(Long id, String email, boolean enabled, String roles) {
        this.id = id;
        this.email = email;
        this.enabled = enabled;
        this.roles = roles;

        if (StringUtils.isNotBlank(this.roles)) {
            final String[] strsRoles = this.roles.split(";");

            for (int i=0; i < strsRoles.length; i++) {
                final int idx = i;
                this.grantedAuthorityList.add((GrantedAuthority) () -> strsRoles[idx]);
            }
        }

        this.grantedAuthorityList.add((GrantedAuthority) () -> GymEscConstants.ROLE_USER);
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return this.grantedAuthorityList;
    }

    @Override
    public String getPassword() {
        return "";
    }

    @Override
    public String getUsername() {
        return email;
    }

    @Override
    public boolean isEnabled() {
        return enabled;
    }

    public Long getId() {
        return id;
    }

    public String getRoles() {
        return roles;
    }
}

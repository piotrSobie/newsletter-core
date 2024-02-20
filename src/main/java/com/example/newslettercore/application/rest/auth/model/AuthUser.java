package com.example.newslettercore.application.rest.auth.model;

import com.example.newslettercore.application.rest.user.model.UserResponse;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;

public class AuthUser extends UserResponse implements UserDetails {

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {

        return this.role.getAuthorities();
    }

    @Override
    public String getUsername() {

        return this.email;
    }

    @Override
    public String getPassword() {

        return this.password;
    }

    @Override
    public boolean isAccountNonExpired() {

        return true;
    }

    @Override
    public boolean isAccountNonLocked() {

        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {

        return true;
    }

    @Override
    public boolean isEnabled() {

        return true;
    }
}

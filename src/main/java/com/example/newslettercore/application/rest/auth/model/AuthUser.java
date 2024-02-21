package com.example.newslettercore.application.rest.auth.model;

import com.example.newslettercore.domain.user.model.Role;
import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;

@Data
public class AuthUser implements UserDetails {

    private String password;

    private String email;

    private Role role;

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

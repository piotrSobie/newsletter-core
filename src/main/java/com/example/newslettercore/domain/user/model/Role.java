package com.example.newslettercore.domain.user.model;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.Set;
import java.util.stream.Collectors;

@RequiredArgsConstructor
public enum Role {

    USER(
            Set.of(
                    Privileges.USER_READ,
                    Privileges.USER_UPDATE,
                    Privileges.USER_CREATE,
                    Privileges.USER_DELETE
            )
    ),
    ADMIN(
            Set.of(
                    Privileges.ADMIN_READ,
                    Privileges.ADMIN_UPDATE,
                    Privileges.ADMIN_CREATE,
                    Privileges.ADMIN_DELETE,
                    Privileges.USER_READ,
                    Privileges.USER_UPDATE,
                    Privileges.USER_CREATE,
                    Privileges.USER_DELETE
            )
    ),
    ;

    @Getter
    private final Set<Privileges> privileges;

    public Set<SimpleGrantedAuthority> getAuthorities() {

        Set<SimpleGrantedAuthority> simpleGrantedAuthorities = getPrivileges().stream()
                .map(privilege -> new SimpleGrantedAuthority(privilege.getPrivilege()))
                .collect(Collectors.toSet());
        simpleGrantedAuthorities.add(new SimpleGrantedAuthority("ROLE_" + this.name()));
        return simpleGrantedAuthorities;
    }
}

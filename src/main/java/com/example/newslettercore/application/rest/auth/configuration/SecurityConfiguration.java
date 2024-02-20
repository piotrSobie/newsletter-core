package com.example.newslettercore.application.rest.auth.configuration;

import com.example.newslettercore.application.rest.auth.filter.JwtAuthenticationFilter;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import static com.example.newslettercore.domain.user.model.Privileges.ADMIN_CREATE;
import static com.example.newslettercore.domain.user.model.Privileges.ADMIN_DELETE;
import static com.example.newslettercore.domain.user.model.Privileges.ADMIN_READ;
import static com.example.newslettercore.domain.user.model.Privileges.ADMIN_UPDATE;
import static com.example.newslettercore.domain.user.model.Privileges.USER_CREATE;
import static com.example.newslettercore.domain.user.model.Privileges.USER_DELETE;
import static com.example.newslettercore.domain.user.model.Privileges.USER_READ;
import static com.example.newslettercore.domain.user.model.Privileges.USER_UPDATE;
import static org.springframework.http.HttpMethod.DELETE;
import static org.springframework.http.HttpMethod.GET;
import static org.springframework.http.HttpMethod.PATCH;
import static org.springframework.http.HttpMethod.POST;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfiguration {

    private static final String[] WHITE_LIST_URL = {"/api/v1/auth/**"};

    private final AuthenticationProvider authenticationProvider;
    private final JwtAuthenticationFilter jwtAuthFilter;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {

        httpSecurity
                .csrf(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests(req ->
                        req.requestMatchers(WHITE_LIST_URL)
                                .permitAll()
                                .requestMatchers(PATCH, "/api/v1/user/**").hasAnyAuthority(USER_UPDATE.getPrivilege())
                                .requestMatchers(GET, "/api/v1/newsletter").hasAnyAuthority(ADMIN_READ.getPrivilege())
                                .requestMatchers(GET, "/api/v1/newsletter/**").hasAnyAuthority(ADMIN_READ.getPrivilege(), USER_READ.getPrivilege())
                                .requestMatchers(POST, "/api/v1/newsletter/**").hasAnyAuthority(ADMIN_CREATE.getPrivilege(), USER_CREATE.getPrivilege())
                                .requestMatchers(PATCH, "/api/v1/newsletter/**").hasAnyAuthority(ADMIN_UPDATE.getPrivilege(), USER_UPDATE.getPrivilege())
                                .requestMatchers(DELETE, "/api/v1/newsletter/**").hasAnyAuthority(ADMIN_DELETE.getPrivilege(), USER_DELETE.getPrivilege())
                                .anyRequest()
                                .authenticated()
                )
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authenticationProvider(authenticationProvider)
                .addFilterBefore(jwtAuthFilter, UsernamePasswordAuthenticationFilter.class);

        return httpSecurity.build();
    }
}

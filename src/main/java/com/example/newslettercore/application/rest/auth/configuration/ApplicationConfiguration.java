package com.example.newslettercore.application.rest.auth.configuration;

import com.example.newslettercore.application.rest.auth.mapper.AuthUserMapper;
import com.example.newslettercore.domain.exception.CantLoginException;
import com.example.newslettercore.domain.user.model.User;
import com.example.newslettercore.domain.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@RequiredArgsConstructor
public class ApplicationConfiguration {

    private final UserService userService;
    private final ApplicationContext applicationContext;

    @Bean
    public UserDetailsService userDetailsService() {

        return username -> {
            User user = userService.findByEmail(username)
                    .orElseThrow(CantLoginException::new);
            return AuthUserMapper.getMapper.userToAuthUser(user);
        };
    }

    @Bean
    public AuthenticationProvider authenticationProvider() {

        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
        UserDetailsService userDetailsService = applicationContext.getBean(UserDetailsService.class);
        authProvider.setUserDetailsService(userDetailsService);
        authProvider.setPasswordEncoder(passwordEncoder());
        return authProvider;
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception {

        return config.getAuthenticationManager();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {

        return new BCryptPasswordEncoder();
    }
}

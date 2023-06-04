package com.app.financetracker.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@EnableWebSecurity
public class SecurityConfiguration {

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth,
                                PasswordEncoder passwordEncoder,
                                UserDetailsService userDetailsService) {
        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
        authProvider.setUserDetailsService(userDetailsService);
        authProvider.setPasswordEncoder(passwordEncoder);
        auth.authenticationProvider(authProvider);
    }

    @Autowired
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        return http.httpBasic().and()
                .cors().and()
                .authorizeRequests()
                .requestMatchers("/auth/login").permitAll()
                .anyRequest().authenticated()
                .and().anonymous().disable()
                .csrf().disable()
                .build();
    }

}

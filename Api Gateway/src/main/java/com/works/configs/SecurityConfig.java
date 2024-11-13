package com.works.configs;

import com.works.services.AdminService;
import jakarta.servlet.http.HttpFilter;
import lombok.Builder;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@RequiredArgsConstructor
public class SecurityConfig {

    final AdminService adminService;
    final PasswordEncoder passwordEncoder;
    final GlobalFilter globalFilter;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        return http
                .httpBasic(Customizer.withDefaults())
                .authorizeHttpRequests(
                        req -> req
                                .requestMatchers("/product/**").hasRole("product")
                                .requestMatchers("/customer/**").hasRole("customer")
                                .requestMatchers("/admin/**").permitAll()
                )
                .csrf( csrf -> csrf.disable() )
                .formLogin( formLogin -> formLogin.disable() )
                .addFilterBefore(globalFilter, UsernamePasswordAuthenticationFilter.class)
                .build();
    }

    @Bean
    public AuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider daoAuthenticationProvider = new DaoAuthenticationProvider();
        daoAuthenticationProvider.setPasswordEncoder(passwordEncoder);
        daoAuthenticationProvider.setUserDetailsService(adminService);
        return daoAuthenticationProvider;
    }


}

/*
ali -> customer
veli -> product
zehra -> product, customer
 */

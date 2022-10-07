package com.kruger.challenge.security;

import org.jetbrains.annotations.NotNull;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true, securedEnabled = true, jsr250Enabled = true)
public class WebSecurityConfiguration {

    @Bean
    public SecurityFilterChain filterChain(@NotNull HttpSecurity http) throws Exception {
        http
                .httpBasic()
                .and()
                .authorizeHttpRequests()
                .antMatchers(HttpMethod.GET, "/api/v1/admin/**").hasRole("ADMIN")
                .antMatchers(HttpMethod.POST, "/api/v1/admin/**").hasRole("ADMIN")
                .antMatchers(HttpMethod.PUT, "/api/v1/admin/**").hasRole("ADMIN")
                .antMatchers(HttpMethod.DELETE, "/api/v1/admin/**").hasRole("ADMIN")
                .antMatchers(HttpMethod.GET, "/api/v1/employee/**").hasRole("EMPLOYEE")
                .antMatchers(HttpMethod.PUT, "/api/v1/employee/**").hasRole("EMPLOYEE")
                .anyRequest().authenticated()
                .and()
                .csrf().disable();
        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }
}

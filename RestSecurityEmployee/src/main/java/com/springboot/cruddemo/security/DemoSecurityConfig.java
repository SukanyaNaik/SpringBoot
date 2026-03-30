package com.springboot.cruddemo.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.core.userdetails.User;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

import javax.sql.DataSource;

@Configuration
public class DemoSecurityConfig {

    // add support for JDBC ... no more hard codded users

    @Bean
    public UserDetailsManager userDetailsManager(DataSource dataSource) {
        return new JdbcUserDetailsManager(dataSource);
    }

//    @Bean
//    public InMemoryUserDetailsManager userDetailsManager() {
//        UserDetails John = User.builder()
//                .username("John")
//                .password("{noop}password123")
//                .roles("EMPLOYEE").build();
//
//        UserDetails Mary = User.builder()
//                .username("Mary")
//                .password("{noop}password123")
//                .roles("EMPLOYEE", "MANAGER").build();
//
//        UserDetails Susan = User.builder()
//                .username("Susan")
//                .password("{noop}password123")
//                .roles("EMPLOYEE", "MANAGER", "ADMIN").build();
//
//        return new InMemoryUserDetailsManager(John, Mary, Susan);
//    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.authorizeHttpRequests(configurer ->
                configurer
                        .requestMatchers(HttpMethod.GET, "/api/employees").hasRole("EMPLOYEE")
                        .requestMatchers(HttpMethod.GET, "/api/employees/**").hasRole("EMPLOYEE")
                        .requestMatchers(HttpMethod.POST, "/api/employees").hasRole("MANAGER")
                        .requestMatchers(HttpMethod.PUT, "/api/employees").hasRole("MANAGER")
                        .requestMatchers(HttpMethod.DELETE, "/api/employees/**").hasRole("ADMIN")

        );

        // Use HTTP Basic Authentication
        http.httpBasic(Customizer.withDefaults());

        // Disable Cross Site Request Forgery (CSRF)
        // in general, not required for stateless REST APIs that use POST, PUT, DELETE and / or PATCH
        http.csrf(csrf -> csrf.disable());

        return http.build();
    }
}

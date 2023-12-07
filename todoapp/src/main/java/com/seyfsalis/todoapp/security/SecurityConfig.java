package com.seyfsalis.todoapp.security;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

import javax.sql.DataSource;

@Configuration
public class SecurityConfig {

    @Bean
    public UserDetailsManager userDetailsManager(DataSource dataSource) {
        JdbcUserDetailsManager jdbcUserDetailsManager = new JdbcUserDetailsManager(dataSource);

        jdbcUserDetailsManager.setUsersByUsernameQuery(
                "select id, username, email, password from users where username=?"
        );

        jdbcUserDetailsManager.setAuthoritiesByUsernameQuery(
                "select user_id, role from roles where user_id=?"
        );

        return jdbcUserDetailsManager;
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

        http.authorizeHttpRequests(configurer ->
                configurer
                        .requestMatchers(HttpMethod.GET, "/api/todos").hasRole("USER")
                        .requestMatchers(HttpMethod.GET, "/api/todos/**").hasRole("USER")
                        .requestMatchers(HttpMethod.POST, "/api/todos").hasRole("USER")
                        .requestMatchers(HttpMethod.PUT, "/api/todos").hasRole("USER")
                        .requestMatchers(HttpMethod.DELETE, "/api/todos/**").hasRole("USER")
                        .requestMatchers(HttpMethod.GET, "/api/todos").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.GET, "/api/todos/**").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.POST, "/api/todos").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.PUT, "/api/todos").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.DELETE, "/api/todos/**").hasRole("ADMIN")
        );

        http.httpBasic(Customizer.withDefaults());

        http.csrf(csrf -> csrf.disable());

        return http.build();
    }

}

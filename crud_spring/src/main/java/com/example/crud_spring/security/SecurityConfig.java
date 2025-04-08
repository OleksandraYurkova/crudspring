package com.example.crud_spring.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.access.expression.method.MethodSecurityExpressionHandler;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(securedEnabled = true)  // Дозволяє використання @Secured
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
            .authorizeRequests(auth -> auth
            
                 // Enable default login form
                .requestMatchers("/home").permitAll()  // Доступ до /home без авторизації
                .requestMatchers("/students").hasAnyRole("USER", "ADMIN")  // Тільки USER та ADMIN
                .requestMatchers("/students/edit/**").hasRole("ADMIN")  // Тільки для ADMIN
                .requestMatchers("/students/delete/**").hasRole("ADMIN")  // Тільки для ADMIN
                .anyRequest().authenticated()  // Інші запити потребують авторизації
            )
            .formLogin()  
            .and()
            .logout().permitAll();  

        return http.build();
    }

    @Bean
    public UserDetailsService userDetailsService() {
        InMemoryUserDetailsManager manager = new InMemoryUserDetailsManager();

        // Створення користувача admin
        UserDetails admin = User.withUsername("admin")
                .password("54321")  // {noop} означає без шифрування
                .roles("ADMIN")
                .build();

        // Створення користувача user
        UserDetails user = User.withUsername("user")
                .password("12345")
                .roles("USER")
                .build();

        manager.createUser(admin);
        manager.createUser(user);

        return manager;
    }
}

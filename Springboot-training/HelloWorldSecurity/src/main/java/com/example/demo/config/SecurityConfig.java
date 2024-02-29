// SecurityConfig.java
package com.example.demo.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfiguration;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfiguration {

    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.inMemoryAuthentication()
            .withUser("admin").password("admin123").roles("ADMIN")
            .and()
            .withUser("user").password("user123").roles("USER");
    }

    protected void configure(HttpSecurity http) throws Exception {
        http
            .authorizeRequests()
                .antMatchers("/admin").hasRole("ADMIN") // Allow access to /admin for users with ADMIN role
                .antMatchers("/user").hasAnyRole("ADMIN", "USER") // Allow access to /user for users with ADMIN or USER role
                .antMatchers("/").permitAll() // Allow access to the home page for all users
                .anyRequest().authenticated() // Require authentication for any other URL
            .and()
            .formLogin(); // Enable form based authentication
    }

    @Bean
    public PasswordEncoder getPasswordEncoder() {
        return NoOpPasswordEncoder.getInstance();
    }
}
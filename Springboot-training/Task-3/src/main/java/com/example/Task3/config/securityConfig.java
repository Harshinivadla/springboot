package com.example.Task3.config;

import com.example.Task3.token.AuthFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableMethodSecurity
@EnableWebSecurity
public class securityConfig {
    @Autowired
    private AuthFilter authFilter;
    @Bean
public UserDetailsService userDetailsService(){

        return new UserInfoUserDetailsService();
}
 @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception { // configure security using httpsecurity obj

        return http.csrf().disable()
                .authorizeHttpRequests()
                .requestMatchers("/api/users/add","/api/users/auth").permitAll()
                .and()
                .authorizeHttpRequests().requestMatchers("/Contacts/**","/Contacts/update/**")
                .authenticated().and()
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .authenticationProvider(authenticationProvider()) // Use the configured authentication provider
                .addFilterBefore(authFilter, UsernamePasswordAuthenticationFilter.class)
                .build();
    }


    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder(); // password ni encode'

    }

    //UserDetailsService ---> user db load
    @Bean
    public AuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider authenticationProvider = new DaoAuthenticationProvider(); // is used to authenticate users based on user details stored in a DAO
        authenticationProvider.setUserDetailsService(userDetailsService());//how to fetch userD
        authenticationProvider.setPasswordEncoder(passwordEncoder());// encoding and storing
        return authenticationProvider;
    }
    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception {
        return config.getAuthenticationManager();
    }
}


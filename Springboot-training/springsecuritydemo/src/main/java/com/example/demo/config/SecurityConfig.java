package com.example.demo.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

	@Bean
    public UserDetailsService userDetailsService(PasswordEncoder encoder) {

//        // InMemoryUserDetailsManager
//        UserDetails admin = User.withUsername("Harshini")
//                .password(encoder.encode("123"))                       
//                .build();                                              
//
//        UserDetails user = User.withUsername("Harshu")
//                .password(encoder.encode("123"))
//                .build();
//        return new InMemoryUserDetailsManager(admin, user);
		
		  return new UserInfoEmployeeDetailsService();
    }

	@Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

		return http
				.authorizeHttpRequests((authorize) -> authorize
						.requestMatchers("employees/all","employees/{id}").authenticated()
						.requestMatchers("employees/welcome","employee/new").permitAll()
						.anyRequest().authenticated()
				)
				
				.formLogin(Customizer.withDefaults())
				.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}

package com.mathschool.MathSchoolBack.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

    @Bean
    SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.csrf(c->c.disable()).cors(c->c.disable()).authorizeHttpRequests(
                    auth->auth.requestMatchers("control/**","content/**").authenticated()
                            .requestMatchers("summary/**").permitAll()
                            .requestMatchers("error/**","css/**", "js/**", "images/**", "fonts/**", "favicon.ico", "webjars/**").permitAll()
                ).
                formLogin(auth->auth.loginPage("/login").loginProcessingUrl("/login")
                        .defaultSuccessUrl("/control").permitAll());
        return http.build();
    }
}

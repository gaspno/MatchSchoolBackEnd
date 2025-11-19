package com.mathschool.MathSchoolBack.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

    @Bean
    SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        /*
         * It is recommended to enable CSRF protection for web applications.
         * CSRF protection can be enabled by removing .csrf(c -> c.disable())
         *
         * CORS configuration should be enabled and configured to allow requests only from trusted domains.
         * Example CORS configuration:
         *
         * http.cors(cors -> cors.configurationSource(corsConfigurationSource()))
         *
         * You would also need to add the corsConfigurationSource() bean:
         *
         * @Bean
         * CorsConfigurationSource corsConfigurationSource() {
         *     CorsConfiguration configuration = new CorsConfiguration();
         *     configuration.setAllowedOrigins(Arrays.asList("https://your-frontend-domain.com"));
         *     configuration.setAllowedMethods(Arrays.asList("GET","POST", "PUT", "DELETE"));
         *     UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
         *     source.registerCorsConfiguration("/**", configuration);
         *     return source;
         * }
         */
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

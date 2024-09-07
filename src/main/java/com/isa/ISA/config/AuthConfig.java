package com.isa.ISA.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.CsrfDsl;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import com.isa.ISA.config.CustomCorsConfiguration;

@Configuration
@EnableWebSecurity
public class AuthConfig {
    @Autowired
    SecurityFilter securityFilter;
    @Autowired CustomCorsConfiguration customCorsConfiguration;

    @Bean
    SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
        return httpSecurity
                //.csrf(csrf -> csrf.disable())
                //.csrf().disable()
                .csrf((csrf) -> csrf.disable())
                .cors((cors) -> cors.disable())
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authorizeHttpRequests(authorize -> authorize
                        .requestMatchers(HttpMethod.POST, "/api/v1/authentication/**").permitAll()
                        .requestMatchers(HttpMethod.POST, "/api/v1/authentication/*").permitAll()
                        .requestMatchers(HttpMethod.GET, "/api/v1/userspace/*").permitAll()
                        .requestMatchers(HttpMethod.GET, "/api/v1/userspace/users/*").permitAll()
                        .requestMatchers(HttpMethod.GET, "/api/v1/userspace/users/**").permitAll()
                        .requestMatchers(HttpMethod.POST, "/api/v1/userspace/*").permitAll()
                        .requestMatchers(HttpMethod.POST, "/api/v1/userspace/email/*").permitAll()
                        .requestMatchers(HttpMethod.POST, "/api/v1/userspace/email/**").permitAll()
                        .requestMatchers(HttpMethod.GET, "/api/v1/userspace/email/**").permitAll()
                        .requestMatchers(HttpMethod.POST, "/api/v1/companies/*").permitAll()
                        .requestMatchers(HttpMethod.POST, "/api/v1/companies/**").permitAll()
                        .requestMatchers(HttpMethod.GET, "/api/v1/companies/*").permitAll()
                        .requestMatchers(HttpMethod.GET, "/api/v1/companies/**").permitAll()
                        .requestMatchers(HttpMethod.GET, "/api/v1/equipments/*").permitAll()
                        .requestMatchers(HttpMethod.GET, "/api/v1/equipments/**").permitAll()
                        //.requestMatchers(HttpMethod.POST, "/api/v1/books").hasRole("ADMIN")
                        //.anyRequest().authenticated())
                        .anyRequest().authenticated())
                .cors(c -> c.configurationSource(customCorsConfiguration))
                .addFilterBefore(securityFilter, UsernamePasswordAuthenticationFilter.class)
                .build();
    }

    @Bean
    AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration)
            throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }

    @Bean
    PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}

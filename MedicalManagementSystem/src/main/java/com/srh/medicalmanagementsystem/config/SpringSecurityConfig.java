package com.srh.medicalmanagementsystem.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

@Configuration
@EnableWebSecurity
public class SpringSecurityConfig {
    @Autowired
    private UserDetailsService userDetailsService;

    @Autowired
    private AuthenticationSuccessHandler customAuthenticationSuccessHandler;

    @Bean
    public static PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .requestMatchers("/register/**", "/index", "/login").permitAll()
                .requestMatchers(HttpMethod.POST,"/patients/delete").hasRole("ADMIN")
                .requestMatchers(HttpMethod.POST,"/medicalRecords/delete").hasRole("ADMIN")
                .requestMatchers("/patients/**").hasRole("ADMIN")
                .requestMatchers("/employees/**").hasRole("ADMIN")
                .requestMatchers(HttpMethod.GET,"/employees/searchDoctors").hasRole("ADMIN")
                .requestMatchers("/medicalRecords/**").hasRole("ADMIN")
                .requestMatchers("/eventDescriptions/**").hasRole("ADMIN")
                .requestMatchers("/patientEventRecord/**").hasRole("ADMIN")
                .anyRequest().authenticated() // Ensures that any other request requires authentication
                .and()
                .formLogin(
                        form -> form
                                .loginPage("/login")
                                .loginProcessingUrl("/perform_login")
                                .successHandler(customAuthenticationSuccessHandler)
                                .permitAll()
                ).logout(
                        logout -> logout
                                .logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
                                .permitAll()
                );
        return http.build();
    }

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth
                .userDetailsService(userDetailsService)
                .passwordEncoder(passwordEncoder());
    }
}

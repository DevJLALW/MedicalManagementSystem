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
                .requestMatchers("/register/**", "/index", "/login", "/patients/forgetPassword/**","/registerEmployee").permitAll()
                .requestMatchers(HttpMethod.POST,"/patients/delete").hasAnyAuthority("ROLE_ADMIN", "ROLE_DOCTOR", "ROLE_NURSE", "ROLE_RECEPTIONIST")
                .requestMatchers(HttpMethod.POST,"/medicalRecords/delete").hasAnyAuthority("ROLE_ADMIN", "ROLE_DOCTOR", "ROLE_NURSE", "ROLE_RECEPTIONIST")
                .requestMatchers("/patients/details/**").hasAnyAuthority("ROLE_PATIENT","ROLE_ADMIN", "ROLE_DOCTOR", "ROLE_NURSE", "ROLE_RECEPTIONIST")
                .requestMatchers("/patients/**").hasAnyAuthority("ROLE_ADMIN", "ROLE_DOCTOR", "ROLE_NURSE", "ROLE_RECEPTIONIST")
                .requestMatchers("/patients/payments").hasAnyAuthority("ROLE_ADMIN", "ROLE_DOCTOR", "ROLE_NURSE", "ROLE_RECEPTIONIST")
                .requestMatchers("/payments/receipt/**").hasAnyAuthority("ROLE_ADMIN", "ROLE_DOCTOR", "ROLE_NURSE", "ROLE_RECEPTIONIST","ROLE_PATIENT")
                .requestMatchers("/employees/**").hasAnyAuthority("ROLE_ADMIN", "ROLE_DOCTOR", "ROLE_NURSE", "ROLE_RECEPTIONIST")
                .requestMatchers("/appointments/**").hasAnyAuthority("ROLE_ADMIN", "ROLE_DOCTOR", "ROLE_NURSE", "ROLE_RECEPTIONIST","ROLE_PATIENT")
                .requestMatchers("/appointments/update/**").hasAnyAuthority("ROLE_ADMIN", "ROLE_DOCTOR", "ROLE_NURSE", "ROLE_RECEPTIONIST")
                .requestMatchers(HttpMethod.GET,"/employees/searchDoctors").hasAnyAuthority("ROLE_ADMIN", "ROLE_DOCTOR", "ROLE_NURSE", "ROLE_RECEPTIONIST")
                .requestMatchers(HttpMethod.GET,"/employees/searchNurse").hasAnyAuthority("ROLE_ADMIN", "ROLE_DOCTOR", "ROLE_NURSE", "ROLE_RECEPTIONIST")
                .requestMatchers("/medicalRecords/**").hasAnyAuthority("ROLE_ADMIN", "ROLE_DOCTOR", "ROLE_NURSE", "ROLE_RECEPTIONIST")
                .requestMatchers("/eventDescriptions/**").hasAnyAuthority("ROLE_ADMIN", "ROLE_DOCTOR", "ROLE_NURSE", "ROLE_RECEPTIONIST")
                .requestMatchers("/patientEventRecord/**").hasAnyAuthority("ROLE_ADMIN", "ROLE_DOCTOR", "ROLE_NURSE", "ROLE_RECEPTIONIST")
                .requestMatchers("/prescriptions/**").hasAnyAuthority("ROLE_ADMIN", "ROLE_DOCTOR", "ROLE_NURSE", "ROLE_RECEPTIONIST","ROLE_PHARMACIST")
                .requestMatchers("/inventories/**").hasAnyAuthority("ROLE_ADMIN","ROLE_PHARMACIST")
                .anyRequest().authenticated()
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

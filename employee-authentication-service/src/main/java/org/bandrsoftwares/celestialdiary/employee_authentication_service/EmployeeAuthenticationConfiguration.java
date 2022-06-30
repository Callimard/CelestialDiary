package org.bandrsoftwares.celestialdiary.employee_authentication_service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.bandrsoftwares.celestialdiary.api.v1.ApiEmployeeV1;
import org.bandrsoftwares.celestialdiary.employee_authentication_service.basic.BasicEmployeeAuthenticationProvider;
import org.bandrsoftwares.celestialdiary.jwt.JWTCompanyAuthenticationDSL;
import org.bandrsoftwares.celestialdiary.jwt.JwtAuthenticationProvider;
import org.bandrsoftwares.celestialdiary.jwt.JwtConfiguration;
import org.bandrsoftwares.celestialdiary.model.mongodb.ModelConfiguration;
import org.bandrsoftwares.celestialdiary.security.SecurityConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;

@Slf4j
@RequiredArgsConstructor
@Configuration
@Import({ModelConfiguration.class, JwtConfiguration.class, SecurityConfiguration.class})
public class EmployeeAuthenticationConfiguration {

    private final BasicEmployeeAuthenticationProvider basicEmployeeAuthenticationProvider;
    private final JwtAuthenticationProvider jwtAuthenticationProvider;

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.httpBasic()
                .and()
                .anonymous().disable()
                .cors().disable()
                .csrf().disable()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .formLogin().disable()
                .logout().disable()
                .authorizeRequests()
                .antMatchers(HttpMethod.POST, ApiEmployeeV1.V1_EMPLOYEE + "/**").authenticated()
                .anyRequest().denyAll();

        http.apply(JWTCompanyAuthenticationDSL.jwtAuthenticationDSL());

        http.authenticationProvider(basicEmployeeAuthenticationProvider).authenticationProvider(jwtAuthenticationProvider);

        return http.build();
    }
}

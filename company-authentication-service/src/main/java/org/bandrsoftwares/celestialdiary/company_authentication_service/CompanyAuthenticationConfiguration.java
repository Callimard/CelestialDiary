package org.bandrsoftwares.celestialdiary.company_authentication_service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.bandrsoftwares.celestialdiary.api.v1.ApiCompanyV1;
import org.bandrsoftwares.celestialdiary.company_authentication_service.basic.BasicCompanyAuthenticationProvider;
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
public class CompanyAuthenticationConfiguration {

    private final BasicCompanyAuthenticationProvider basicCompanyAuthenticationProvider;
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
                .antMatchers(HttpMethod.POST, ApiCompanyV1.V1_COMPANY + "/**").authenticated()
                .anyRequest().denyAll();

        http.apply(JWTCompanyAuthenticationDSL.jwtAuthenticationDSL());

        http.authenticationProvider(basicCompanyAuthenticationProvider).authenticationProvider(jwtAuthenticationProvider);

        return http.build();
    }
}


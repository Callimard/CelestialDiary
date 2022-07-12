package org.bandrsoftwares.celestialdiary.authentication_service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
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

import static org.bandrsoftwares.celestialdiary.api.v1.AuthenticationV1.AUTHENTICATION_URL;

@Slf4j
@RequiredArgsConstructor
@Configuration
@Import({ModelConfiguration.class, SecurityConfiguration.class, JwtConfiguration.class})
public class AuthenticationConfiguration {

    private final BasicAuthenticationProvider basicAuthenticationProvider;
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
                .antMatchers(HttpMethod.OPTIONS, "/**").permitAll()
                .antMatchers(HttpMethod.POST, AUTHENTICATION_URL + "/**").authenticated()
                .anyRequest().denyAll();

        http.headers()
                .xssProtection()
                .and()
                .contentSecurityPolicy("script-src 'self'");

        http.authenticationProvider(jwtAuthenticationProvider).authenticationProvider(basicAuthenticationProvider);

        http.apply(JWTCompanyAuthenticationDSL.jwtAuthenticationDSL());

        return http.build();
    }
}

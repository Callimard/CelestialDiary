package org.bandrsoftwares.celestialdiary.company_management_service;

import lombok.RequiredArgsConstructor;
import org.bandrsoftwares.celestialdiary.aop.AopConfiguration;
import org.bandrsoftwares.celestialdiary.jwt.JWTCompanyAuthenticationDSL;
import org.bandrsoftwares.celestialdiary.jwt.JwtConfiguration;
import org.bandrsoftwares.celestialdiary.model.mongodb.ModelConfiguration;
import org.bandrsoftwares.celestialdiary.security.SecurityConfiguration;
import org.bandrsoftwares.celestialdiary.utils.UtilsConfiguration;
import org.bandrsoftwares.celestialdiary.validation.ValidationConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;

import static org.bandrsoftwares.celestialdiary.api.v1.ApiCompanyV1.COMPANIES_URL;
import static org.bandrsoftwares.celestialdiary.api.v1.ApiPrivilegeV1.PRIVILEGES_URL;

@RequiredArgsConstructor
@Configuration
@Import({ModelConfiguration.class, JwtConfiguration.class, SecurityConfiguration.class, AopConfiguration.class, ValidationConfiguration.class,
         UtilsConfiguration.class})
public class CompanyManagementConfiguration {

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.httpBasic()
                .and()
                .cors().disable()
                .csrf().disable()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .formLogin().disable()
                .logout().disable()
                .authorizeRequests()
                .antMatchers(HttpMethod.OPTIONS, "/**").permitAll()
                .antMatchers(HttpMethod.GET, PRIVILEGES_URL + "/**").permitAll()
                .antMatchers(HttpMethod.GET, COMPANIES_URL + "/**").authenticated()
                .antMatchers(HttpMethod.POST, COMPANIES_URL + "/**").authenticated()
                .antMatchers(HttpMethod.PUT, COMPANIES_URL + "/**").authenticated()
                .antMatchers(HttpMethod.DELETE, COMPANIES_URL + "/**").authenticated()
                .anyRequest().denyAll();

        http.apply(JWTCompanyAuthenticationDSL.jwtAuthenticationDSL());

        return http.build();
    }
}

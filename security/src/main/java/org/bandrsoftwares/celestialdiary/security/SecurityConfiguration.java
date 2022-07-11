package org.bandrsoftwares.celestialdiary.security;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.boot.autoconfigure.AutoConfigurationPackage;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;

import java.util.List;

@Getter
@Setter
@RequiredArgsConstructor
@Configuration
@AutoConfigurationPackage
@ComponentScan("org.bandrsoftwares.celestialdiary.security")
@EnableConfigurationProperties
@ConfigurationProperties(prefix = "security")
@EnableGlobalMethodSecurity(
        prePostEnabled = true,
        securedEnabled = true,
        jsr250Enabled = true)
public class SecurityConfiguration {

    private List<String> corsOrigins;
}

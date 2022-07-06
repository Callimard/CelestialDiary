package org.bandrsoftwares.celestialdiary.security;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.autoconfigure.AutoConfigurationPackage;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Getter
@Setter
@Configuration
@AutoConfigurationPackage
@ComponentScan("org.bandrsoftwares.celestialdiary.security")
@EnableConfigurationProperties
@ConfigurationProperties(prefix = "security")
public class SecurityConfiguration {

    private List<String> corsOrigins;
}

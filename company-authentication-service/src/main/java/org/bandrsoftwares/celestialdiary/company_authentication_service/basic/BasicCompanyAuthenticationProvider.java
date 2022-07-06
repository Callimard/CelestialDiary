package org.bandrsoftwares.celestialdiary.company_authentication_service.basic;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.bandrsoftwares.celestialdiary.model.mongodb.company.Company;
import org.bandrsoftwares.celestialdiary.model.mongodb.company.CompanyRepository;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Slf4j
@RequiredArgsConstructor
@Component
public class BasicCompanyAuthenticationProvider implements AuthenticationProvider {

    private final CompanyRepository companyRepository;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        String companyEmail = authentication.getName();
        String password = authentication.getCredentials().toString();

        Optional<Company> opCompany = companyRepository.findByEmail(companyEmail);
        if (opCompany.isPresent()) {
            Company company = opCompany.get();
            if (company.getPassword().equals(password)) {
                return new UsernamePasswordAuthenticationToken(company, authentication.getCredentials(), null);
            } else {
                log.info("BASIC AUTH FAIL -> Bad Credentials / Details: {}", authentication.getDetails());
                throw new BadCredentialsException("Company Authentication failed for " + companyEmail);
            }
        } else {
            log.info("BASIC AUTH FAIL -> Unknown Company {} / Details: {}", companyEmail, authentication.getDetails());
            throw new BadCredentialsException("Company Authentication failed for " + companyEmail);
        }
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return authentication.equals(UsernamePasswordAuthenticationToken.class);
    }
}

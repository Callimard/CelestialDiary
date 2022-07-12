package org.bandrsoftwares.celestialdiary.authentication_service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.bandrsoftwares.celestialdiary.model.mongodb.company.Company;
import org.bandrsoftwares.celestialdiary.model.mongodb.company.CompanyRepository;
import org.bandrsoftwares.celestialdiary.model.mongodb.employee.Employee;
import org.bandrsoftwares.celestialdiary.model.mongodb.employee.EmployeeRepository;
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
public class BasicAuthenticationProvider implements AuthenticationProvider {

    // Variables.

    private final EmployeeRepository employeeRepository;
    private final CompanyRepository companyRepository;

    // Methods.

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        String authenticationName = authentication.getName();

        if (isCompanyAuthentication(authenticationName)) {
            return authenticateCompany(authentication);
        } else if (isEmployeeAuthentication(authenticationName)) {
            return authenticateEmployee(authentication);
        } else {
            log.info("BASIC AUTH FAIL -> Unknown authentication type {}", authenticationName.charAt(0));
            throw new BadCredentialsException("Unknown authentication type \"" + authenticationName.charAt(0) + "\"");
        }
    }

    private BasicCompanyAuthentication authenticateCompany(Authentication authentication) {
        String companyEmail = authentication.getName().substring(1);
        String password = authentication.getCredentials().toString();

        Optional<Company> opCompany = companyRepository.findByEmail(companyEmail);
        if (opCompany.isPresent()) {
            Company company = opCompany.get();
            if (company.getPassword().equals(password)) {
                BasicCompanyAuthentication auth = new BasicCompanyAuthentication(company, password);
                auth.setDetails(authentication.getDetails());
                return auth;
            } else {
                log.info("BASIC COMPANY AUTH FAIL -> Bad Credentials / Details: {}", authentication.getDetails());
                throw new BadCredentialsException("Company Authentication failed for " + companyEmail);
            }
        } else {
            log.info("BASIC COMPANY AUTH FAIL -> Unknown Company {} / Details: {}", companyEmail, authentication.getDetails());
            throw new BadCredentialsException("Company Authentication failed for " + companyEmail);
        }
    }

    private BasicEmployeeAuthentication authenticateEmployee(Authentication authentication) {
        String authenticationName = authentication.getName().substring(1);
        String[] split = authenticationName.split("\\|");
        if (split.length < 2) {
            log.info("BASIC AUTH FAIL -> Mal formatted Credentials / Details: {}", authentication.getDetails());
            throw new BadCredentialsException("Authentication name not correctly formatted. Format -> companyName|employeeEmail:password");
        }
        String companyName = split[0].replace(" ", "").toLowerCase();
        String employeeEmail = split[1];
        String password = authentication.getCredentials().toString();

        Optional<Company> opCompany = companyRepository.findByName(companyName);
        if (opCompany.isPresent()) {
            Company company = opCompany.get();
            Optional<Employee> opEmployee = employeeRepository.findByCompanyAndEmail(company, employeeEmail);
            if (opEmployee.isPresent()) {
                Employee employee = opEmployee.get();
                if (employee.getPassword().equals(password)) {
                    BasicEmployeeAuthentication auth = new BasicEmployeeAuthentication(employee, authentication.getCredentials());
                    auth.setDetails(auth.getDetails());
                    return auth;
                } else {
                    log.info("BASIC EMPLOYEE AUTH FAIL -> Bad Credentials / Details: {}", authentication.getDetails());
                    throw new BadCredentialsException("Employee Authentication failed for " + employeeEmail + " in the company " + company.getName());
                }
            } else {
                log.info("BASIC EMPLOYEE AUTH FAIL -> Unknown Employee / Details: {}", authentication.getDetails());
                throw new BadCredentialsException("Employee Authentication failed for " + employeeEmail + " in the company " + company.getName());
            }
        } else {
            log.info("BASIC EMPLOYEE AUTH FAIL -> Unknown company / Details: {}", authentication.getDetails());
            throw new BadCredentialsException("Employee Authentication failed");
        }
    }

    private boolean isCompanyAuthentication(String authenticationName) {
        return authenticationName.startsWith(AuthenticationType.COMPANY_AUTHENTICATION.getIdentifier());
    }

    private boolean isEmployeeAuthentication(String authenticationName) {
        return authenticationName.startsWith(AuthenticationType.EMPLOYEE_AUTHENTICATION.getIdentifier());
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return authentication.equals(UsernamePasswordAuthenticationToken.class);
    }

    // Inner classes.

    public enum AuthenticationType {
        COMPANY_AUTHENTICATION("C"),
        EMPLOYEE_AUTHENTICATION("E");

        private final String identifier;

        AuthenticationType(String identifier) {
            this.identifier = identifier;
        }

        public String getIdentifier() {
            return identifier;
        }
    }
}

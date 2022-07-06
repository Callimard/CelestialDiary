package org.bandrsoftwares.celestialdiary.employee_authentication_service.basic;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.bandrsoftwares.celestialdiary.model.mongodb.company.Company;
import org.bandrsoftwares.celestialdiary.model.mongodb.company.CompanyRepository;
import org.bandrsoftwares.celestialdiary.model.mongodb.person.employee.Employee;
import org.bandrsoftwares.celestialdiary.model.mongodb.person.employee.EmployeeRepository;
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
public class BasicEmployeeAuthenticationProvider implements AuthenticationProvider {

    private final CompanyRepository companyRepository;
    private final EmployeeRepository employeeRepository;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        String authenticationName = authentication.getName();
        String[] split = authenticationName.split("\\|");
        if (split.length != 2) {
            log.info("BASIC AUTH FAIL -> Mal formatted Credentials / Details: {}", authentication.getDetails());
            throw new BadCredentialsException("Authentication name not correctly formatted. Format -> companyName|employeeEmail:password");
        }
        String companyName = split[0].replace(" ", "").toLowerCase();
        String employeeEmail = split[1];
        String password = authentication.getCredentials().toString();

        Optional<Company> opCompany = companyRepository.findByName(companyName);
        if (opCompany.isPresent()) {
            Company company = opCompany.get();
            Optional<Employee> opEmployee = employeeRepository.findByCompanySummaryCompanyAndEmail(company, employeeEmail);
            if (opEmployee.isPresent()) {
                Employee employee = opEmployee.get();
                if (employee.getPassword().equals(password)) {
                    return new UsernamePasswordAuthenticationToken(employee, authentication.getCredentials(), null);
                } else {
                    log.info("BASIC AUTH FAIL -> Bad Credentials / Details: {}", authentication.getDetails());
                    throw new BadCredentialsException("Employee Authentication failed for " + employeeEmail + " in the company " + company.getName());
                }
            } else {
                log.info("BASIC AUTH FAIL -> Unknown Employee / Details: {}", authentication.getDetails());
                throw new BadCredentialsException("Employee Authentication failed for " + employeeEmail + " in the company " + company.getName());
            }
        } else {
            log.info("BASIC AUTH FAIL -> Unknown company / Details: {}", authentication.getDetails());
            throw new BadCredentialsException("Employee Authentication failed");
        }
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return authentication.equals(UsernamePasswordAuthenticationToken.class);
    }
}

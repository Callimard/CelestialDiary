package org.bandrsoftwares.celestialdiary.jwt;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.collect.Lists;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.bandrsoftwares.celestialdiary.model.mongodb.company.Company;
import org.bandrsoftwares.celestialdiary.model.mongodb.employee.Employee;
import org.bandrsoftwares.celestialdiary.security.privilege.company.CompanyManagementPrivilege;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.UUID;

@Slf4j
@RequiredArgsConstructor
@Service
public class JwtCreatorService {

    // Variables.

    private final JwtConfiguration jwtConfiguration;

    private final Algorithm hmac256;

    private final ObjectMapper mapper;

    // Methods.

    public String createJwtFor(Company company) {
        Instant now = Instant.now();
        JwtAccount jwtAccount = buildJwtAccount(company);
        try {
            return buildJwt(now, jwtAccount);
        } catch (JsonProcessingException e) {
            log.error("Fail to serialize " + JwtAccount.class + " in JSON with Jackson, Company " + company, e);
            return "";
        }
    }

    public String createJwtFor(Employee employee) {
        Instant now = Instant.now();
        JwtAccount jwtAccount = buildJwtAccount(employee);
        try {
            return buildJwt(now, jwtAccount);
        } catch (JsonProcessingException e) {
            log.error("Fail to serialize " + JwtAccount.class + " in JSON with Jackson, Employee " + employee, e);
            return "";
        }
    }

    public String createJwtFor(JwtAccount jwtAccount) {
        try {
            return buildJwt(Instant.now(), jwtAccount);
        } catch (JsonProcessingException e) {
            log.error("Fail to serialize " + JwtAccount.class + " in JSON with Jackson", e);
            return "";
        }
    }

    private String buildJwt(Instant now, JwtAccount jwtAccount) throws JsonProcessingException {
        return JWT.create()
                .withIssuedAt(now)
                .withExpiresAt(now.plus(jwtConfiguration.getDefaultTimeExpiration(), ChronoUnit.MINUTES))
                .withJWTId(UUID.randomUUID().toString())
                .withClaim(JwtAccount.CLAIM_ACCOUNT, mapper.writeValueAsString(jwtAccount))
                .sign(hmac256);
    }

    private JwtAccount buildJwtAccount(Company company) {
        return JwtAccount.builder()
                .companyId(company.getId())
                .companyEmail(company.getEmail())
                .companyName(company.getName())
                .accountAuthorities(Lists.newArrayList(CompanyManagementPrivilege.COMPANY_ALL.getPrivilege(company.getId())))
                .build();
    }

    private JwtAccount buildJwtAccount(Employee employee) {
        return JwtAccount.builder()
                .companyId(employee.getCompany().getId())
                .companyEmail(employee.getCompany().getEmail())
                .companyName(employee.getCompany().getName())
                .employeeEmail(employee.getEmail())
                .employeeFirstName(employee.getFirstName())
                .employeeLastName(employee.getLastName())
                .accountAuthorities(Lists.newArrayList(employee.allAuthorities()))
                .build();
    }
}

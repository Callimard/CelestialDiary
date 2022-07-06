package org.bandrsoftwares.celestialdiary.employee_authentication_service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.bandrsoftwares.celestialdiary.api.v1.ApiEmployeeV1;
import org.bandrsoftwares.celestialdiary.jwt.JwtAPIWrongAuthenticationPrincipalException;
import org.bandrsoftwares.celestialdiary.jwt.JwtAccount;
import org.bandrsoftwares.celestialdiary.jwt.JwtCreatorService;
import org.bandrsoftwares.celestialdiary.jwt.JwtTokenResponse;
import org.bandrsoftwares.celestialdiary.model.mongodb.person.employee.Employee;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping(ApiEmployeeV1.V1_EMPLOYEE)
public class EmployeeAuthenticationTokenController {

    private final JwtCreatorService jwtCreatorService;

    @PostMapping(ApiEmployeeV1.TOKEN)
    public JwtTokenResponse loginToken(Authentication authentication) {
        if (authentication.getPrincipal() instanceof Employee employee) {
            log.info("Generate login token for Employee {}", employee.getEmail());
            return new JwtTokenResponse(jwtCreatorService.createJwtFor(employee));
        } else {
            throw new JwtAPIWrongAuthenticationPrincipalException("For /employee/token, use BASIC authentication");
        }
    }

    @PostMapping(ApiEmployeeV1.TOKEN_REFRESH)
    public JwtTokenResponse refreshToken(Authentication authentication) {
        if (authentication.getPrincipal() instanceof JwtAccount jwtAccount) {
            log.info("Refresh token for Employee {}", jwtAccount.getEmployeeEmail());
            return new JwtTokenResponse(jwtCreatorService.createJwtFor(jwtAccount));
        } else {
            throw new JwtAPIWrongAuthenticationPrincipalException("For /employee/token/refresh, use BEARER authentication");
        }
    }
}

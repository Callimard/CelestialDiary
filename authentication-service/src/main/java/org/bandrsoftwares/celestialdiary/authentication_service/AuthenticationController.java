package org.bandrsoftwares.celestialdiary.authentication_service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.bandrsoftwares.celestialdiary.jwt.JwtAPIWrongAuthenticationPrincipalException;
import org.bandrsoftwares.celestialdiary.jwt.JwtAccount;
import org.bandrsoftwares.celestialdiary.jwt.JwtCreatorService;
import org.bandrsoftwares.celestialdiary.jwt.JwtTokenResponse;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static org.bandrsoftwares.celestialdiary.api.v1.AuthenticationV1.*;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping(AUTHENTICATION_URL)
public class AuthenticationController {

    private final JwtCreatorService jwtCreatorService;

    // Methods.

    @PostMapping(COMPANY_AUTHENTICATION)
    public JwtTokenResponse companyLoginToken(Authentication authentication) {
        if (authentication instanceof BasicCompanyAuthentication companyAuth) {
            log.info("Generate login token for Company {} / Details {}", companyAuth.getPrincipal().getEmail(), authentication.getDetails());
            return new JwtTokenResponse(jwtCreatorService.createJwtFor(companyAuth.getPrincipal()));
        } else {
            log.error("COMPANY AUTH FAIL -> wrong authentication type on {}, must be BASIC authentication / Details {}", COMPANY_AUTHENTICATION_URL
                    , authentication.getDetails());
            throw new JwtAPIWrongAuthenticationPrincipalException("For " + COMPANY_AUTHENTICATION_URL + ", use BASIC authentication");
        }
    }

    @PostMapping(COMPANY_TOKEN_REFRESH)
    public JwtTokenResponse companyRefreshToken(Authentication authentication) {
        if (authentication.getPrincipal() instanceof JwtAccount jwtAccount) {
            log.info("Refresh token for Company {} / Details {}", jwtAccount.getCompanyEmail(), authentication.getDetails());
            return new JwtTokenResponse(jwtCreatorService.createJwtFor(jwtAccount));
        } else {
            log.error("COMPANY AUTH FAIL -> wrong authentication type on {}, must be BEARER authentication / Details {}", COMPANY_TOKEN_REFRESH_URL
                    , authentication.getDetails());
            throw new JwtAPIWrongAuthenticationPrincipalException("For " + COMPANY_TOKEN_REFRESH_URL + ", use BEARER authentication");
        }
    }

    @PostMapping(EMPLOYEE_AUTHENTICATION)
    public JwtTokenResponse employeeLoginToken(Authentication authentication) {
        if (authentication instanceof BasicEmployeeAuthentication employeeAuth) {
            log.info("Generate login token for Employee {} / Details {}", employeeAuth.getPrincipal().getEmail(), authentication.getDetails());
            return new JwtTokenResponse(jwtCreatorService.createJwtFor(employeeAuth.getPrincipal()));
        } else {
            log.error("EMPLOYEE AUTH FAIL -> wrong authentication type on {}, must be BASIC authentication / Details {}", EMPLOYEE_AUTHENTICATION_URL
                    , authentication.getDetails());
            throw new JwtAPIWrongAuthenticationPrincipalException(
                    "For " + EMPLOYEE_AUTHENTICATION_URL + ", use BASIC authentication");
        }
    }

    @PostMapping(EMPLOYEE_TOKEN_REFRESH)
    public JwtTokenResponse employeeRefreshToken(Authentication authentication) {
        if (authentication.getPrincipal() instanceof JwtAccount jwtAccount) {
            log.info("Refresh token for Employee {}  / Details {}", jwtAccount.getEmployeeEmail(), authentication.getDetails());
            return new JwtTokenResponse(jwtCreatorService.createJwtFor(jwtAccount));
        } else {
            log.error("EMPLOYEE AUTH FAIL -> wrong authentication type on {}, must be BEARER authentication / Details {}", EMPLOYEE_TOKEN_REFRESH_URL
                    , authentication.getDetails());
            throw new JwtAPIWrongAuthenticationPrincipalException(
                    "For " + EMPLOYEE_TOKEN_REFRESH_URL + ", use BEARER authentication");
        }
    }
}

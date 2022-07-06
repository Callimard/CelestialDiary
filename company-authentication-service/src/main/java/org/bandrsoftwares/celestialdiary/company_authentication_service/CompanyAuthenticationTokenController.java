package org.bandrsoftwares.celestialdiary.company_authentication_service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.bandrsoftwares.celestialdiary.api.v1.ApiCompanyV1;
import org.bandrsoftwares.celestialdiary.jwt.JwtAPIWrongAuthenticationPrincipalException;
import org.bandrsoftwares.celestialdiary.jwt.JwtAccount;
import org.bandrsoftwares.celestialdiary.jwt.JwtCreatorService;
import org.bandrsoftwares.celestialdiary.jwt.JwtTokenResponse;
import org.bandrsoftwares.celestialdiary.model.mongodb.company.Company;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping(ApiCompanyV1.V1_COMPANY)
public class CompanyAuthenticationTokenController {

    private final JwtCreatorService jwtCreatorService;

    @PostMapping(ApiCompanyV1.TOKEN)
    public JwtTokenResponse loginToken(Authentication authentication) {
        if (authentication.getPrincipal() instanceof Company company) {
            log.info("Generate login token for Company {}", company.getEmail());
            return new JwtTokenResponse(jwtCreatorService.createJwtFor(company));
        } else {
            throw new JwtAPIWrongAuthenticationPrincipalException("For /company/token, use BASIC authentication");
        }
    }

    @PostMapping(ApiCompanyV1.TOKEN_REFRESH)
    public JwtTokenResponse refreshToken(Authentication authentication) {
        if (authentication.getPrincipal() instanceof JwtAccount jwtAccount) {
            log.info("Refresh token for Company {}", jwtAccount.getCompanyEmail());
            return new JwtTokenResponse(jwtCreatorService.createJwtFor(jwtAccount));
        } else {
            throw new JwtAPIWrongAuthenticationPrincipalException("For /company/token/refresh, use BEARER authentication");
        }
    }
}

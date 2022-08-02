package org.bandrsoftwares.celestialdiary.jwt;

import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.collect.Lists;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.bandrsoftwares.celestialdiary.security.privilege.company.CompanyManagementScope;
import org.bandrsoftwares.celestialdiary.security.privilege.company.CompanyScopePrivilege;
import org.bandrsoftwares.celestialdiary.security.privilege.company.establishment.EstablishmentManagementScope;
import org.bandrsoftwares.celestialdiary.security.privilege.establishment.EstablishmentScopePrivilege;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.oauth2.server.resource.BearerTokenAuthenticationToken;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

import static org.bandrsoftwares.celestialdiary.jwt.JwtAccount.CLAIM_ACCOUNT;

@Slf4j
@RequiredArgsConstructor
@Component
public class JwtAuthenticationProvider implements AuthenticationProvider {

    private final JWTVerifier jwtVerifier;

    private final ObjectMapper mapper;

    private final CompanyManagementScope companyManagementScope = new CompanyManagementScope();
    private final EstablishmentManagementScope establishmentManagementScope = new EstablishmentManagementScope();

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        BearerTokenAuthenticationToken bearerAuth = (BearerTokenAuthenticationToken) authentication;

        try {
            DecodedJWT decodedJWT = jwtVerifier.verify(bearerAuth.getToken());
            JwtAccount jwtAccount = mapper.readValue(decodedJWT.getClaim(CLAIM_ACCOUNT).asString(), JwtAccount.class);
            UsernamePasswordAuthenticationToken auth =
                    new UsernamePasswordAuthenticationToken(jwtAccount, decodedJWT.getToken(), extractAuthorities(jwtAccount));
            auth.setDetails(bearerAuth.getDetails());
            return auth;
        } catch (JWTVerificationException | JsonProcessingException e) {
            log.info("JWT AUTH FAIL -> Details: {} / Reason: {}", authentication.getDetails() != null ? authentication.getDetails().toString() : null,
                     e.getMessage());
            throw new BadCredentialsException("JWT is not correct", e);
        }
    }

    private List<SimpleGrantedAuthority> extractAuthorities(JwtAccount jwtAccount) {
        List<SimpleGrantedAuthority> authorities = Lists.newArrayList();
        authorities.addAll(extractCompanyAuthorities(jwtAccount));
        authorities.addAll(extractEstablishmentAuthorities(jwtAccount));
        return authorities;
    }

    private List<SimpleGrantedAuthority> extractCompanyAuthorities(JwtAccount jwtAccount) {
        List<SimpleGrantedAuthority> authorities = Lists.newArrayList();

        if (jwtAccount.getCompanyPrivilegeIdentifiers() != null) {
            for (String identifier : jwtAccount.getCompanyPrivilegeIdentifiers()) {
                try {
                    CompanyScopePrivilege scopePrivilege = companyManagementScope.scopePrivilegeOf(identifier);
                    authorities.add(new SimpleGrantedAuthority(scopePrivilege.formatPrivilege(jwtAccount.getCompanyId())));
                } catch (IllegalArgumentException e) {
                    log.error("Unknown CompanyManagementPrivilege identifier", e);
                }
            }
        }

        return authorities;
    }

    private List<SimpleGrantedAuthority> extractEstablishmentAuthorities(JwtAccount jwtAccount) {
        List<SimpleGrantedAuthority> authorities = Lists.newArrayList();

        if (jwtAccount.getEstablishmentPrivilegeIdentifiers() != null) {
            for (Map.Entry<String, List<String>> entry : jwtAccount.getEstablishmentPrivilegeIdentifiers().entrySet()) {
                String establishmentId = entry.getKey();
                for (String identifier : entry.getValue()) {
                    try {
                        EstablishmentScopePrivilege scopePrivilege = establishmentManagementScope.scopePrivilegeOf(identifier);
                        authorities.add(new SimpleGrantedAuthority(scopePrivilege.formatPrivilege(jwtAccount.getCompanyId(), establishmentId)));
                    } catch (IllegalArgumentException e) {
                        log.error("Unknown EstablishmentManagementPrivilege identifier", e);
                    }
                }
            }
        }

        return authorities;
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return authentication.equals(BearerTokenAuthenticationToken.class);
    }
}

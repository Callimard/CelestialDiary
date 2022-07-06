package org.bandrsoftwares.celestialdiary.jwt;

import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.oauth2.server.resource.BearerTokenAuthenticationToken;
import org.springframework.stereotype.Component;

import java.util.List;

import static org.bandrsoftwares.celestialdiary.jwt.JwtAccount.CLAIM_ACCOUNT;

@Slf4j
@RequiredArgsConstructor
@Component
public class JwtAuthenticationProvider implements AuthenticationProvider {

    private final JWTVerifier jwtVerifier;

    private final ObjectMapper mapper;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        BearerTokenAuthenticationToken auth = (BearerTokenAuthenticationToken) authentication;

        try {
            DecodedJWT decodedJWT = jwtVerifier.verify(auth.getToken());
            JwtAccount jwtAccount = mapper.readValue(decodedJWT.getClaim(CLAIM_ACCOUNT).asString(), JwtAccount.class);
            return new UsernamePasswordAuthenticationToken(jwtAccount, decodedJWT.getToken(), extractAuthorities(jwtAccount));
        } catch (JWTVerificationException | JsonProcessingException e) {
            log.info("JWT AUTH FAIL -> Details: {} / Reason: {}", authentication.getDetails() != null ? authentication.getDetails().toString() :
                             null,
                     e.getMessage());
            throw new BadCredentialsException("JWT is not correct", e);
        }
    }

    private List<SimpleGrantedAuthority> extractAuthorities(JwtAccount jwtAccount) {
        return jwtAccount.getAccountAuthorities() != null ? jwtAccount.getAccountAuthorities().stream()
                .map(SimpleGrantedAuthority::new).toList() : null;
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return authentication.equals(BearerTokenAuthenticationToken.class);
    }
}

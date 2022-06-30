package org.bandrsoftwares.celestialdiary.jwt;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST)
public class JwtAPIWrongAuthenticationPrincipalException extends RuntimeException {
    public JwtAPIWrongAuthenticationPrincipalException(String message) {
        super(message);
    }
}

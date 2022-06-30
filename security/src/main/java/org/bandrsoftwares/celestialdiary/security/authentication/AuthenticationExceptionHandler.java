package org.bandrsoftwares.celestialdiary.security.authentication;

import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.nio.file.AccessDeniedException;

@Slf4j
@ControllerAdvice
public class AuthenticationExceptionHandler {

    @ExceptionHandler(value = {BadCredentialsException.class})
    public void badCredentials(HttpServletResponse response) throws IOException {
        response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Bad credentials");
    }

    @ExceptionHandler(value = {AccessDeniedException.class})
    public void accessDenied(HttpServletResponse response) throws IOException {
        response.sendError(HttpServletResponse.SC_FORBIDDEN, "Access denied");
    }
}

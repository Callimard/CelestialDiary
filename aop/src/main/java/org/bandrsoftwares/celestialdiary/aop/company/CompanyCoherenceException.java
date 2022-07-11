package org.bandrsoftwares.celestialdiary.aop.company;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class CompanyCoherenceException extends RuntimeException{

    public CompanyCoherenceException(String msg) {
        super(msg);
    }
}

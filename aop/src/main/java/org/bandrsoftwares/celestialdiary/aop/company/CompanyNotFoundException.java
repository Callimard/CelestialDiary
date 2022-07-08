package org.bandrsoftwares.celestialdiary.aop.company;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class CompanyNotFoundException extends RuntimeException {
    public CompanyNotFoundException(String companyId) {
        super("The company with the id " + companyId + " has not be found");
    }
}

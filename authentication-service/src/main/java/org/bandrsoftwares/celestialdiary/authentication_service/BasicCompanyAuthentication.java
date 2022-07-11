package org.bandrsoftwares.celestialdiary.authentication_service;

import lombok.NonNull;
import org.bandrsoftwares.celestialdiary.model.mongodb.company.Company;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;

public class BasicCompanyAuthentication extends UsernamePasswordAuthenticationToken {

    // Constructors.

    public BasicCompanyAuthentication(@NonNull Company principal, Object credentials) {
        super(principal, credentials, null);
    }

    // Methods.

    // Methods.

    @Override
    public Company getPrincipal() {
        return (Company) super.getPrincipal();
    }
}

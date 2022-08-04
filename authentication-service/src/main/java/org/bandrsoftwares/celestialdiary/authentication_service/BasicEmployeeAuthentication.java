package org.bandrsoftwares.celestialdiary.authentication_service;

import lombok.NonNull;
import org.bandrsoftwares.celestialdiary.model.mongodb.person.employee.Employee;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;

public class BasicEmployeeAuthentication extends UsernamePasswordAuthenticationToken {

    // Constructors.

    public BasicEmployeeAuthentication(@NonNull Employee principal, Object credentials) {
        super(principal, credentials, null);
    }

    // Methods.

    @Override
    public Employee getPrincipal() {
        return (Employee) super.getPrincipal();
    }
}

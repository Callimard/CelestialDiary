package org.bandrsoftwares.celestialdiary.security.privilege.company.establishment;

import org.springframework.security.access.prepost.PreAuthorize;

import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@SuppressWarnings({"SpringElInspection", "ELValidationInJSP"})
@Inherited
@Retention(RetentionPolicy.RUNTIME)
@PreAuthorize("hasAnyAuthority('company:' + #idCompany + ':establishment:activate', 'company:' + #idCompany + ':establishment:all', 'company:' + #idCompany + ':all')")
public @interface ActivateCompanyEstablishmentPrivilege {
}

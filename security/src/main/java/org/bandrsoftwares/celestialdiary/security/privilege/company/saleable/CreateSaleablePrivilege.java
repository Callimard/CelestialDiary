package org.bandrsoftwares.celestialdiary.security.privilege.company.saleable;

import org.springframework.security.access.prepost.PreAuthorize;

import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@SuppressWarnings({"SpringElInspection", "ELValidationInJSP"})
@Inherited
@Retention(RetentionPolicy.RUNTIME)
@PreAuthorize("hasAnyAuthority('company:' + #idCompany + ':saleable:create', 'company:' + #idCompany + ':saleable:all', 'company:' + #idCompany + " +
        "':all')")
public @interface CreateSaleablePrivilege {
}

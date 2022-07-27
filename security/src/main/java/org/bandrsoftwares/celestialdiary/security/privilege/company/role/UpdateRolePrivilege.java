package org.bandrsoftwares.celestialdiary.security.privilege.company.role;

import org.springframework.security.access.prepost.PreAuthorize;

import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@SuppressWarnings({"SpringElInspection", "ELValidationInJSP"})
@Inherited
@Retention(RetentionPolicy.RUNTIME)
@PreAuthorize("hasAnyAuthority('company:' + #idCompany + ':role:update', 'company:' + #idCompany + ':role:all', 'company:' + #idCompany + " +
        "':all')")
public @interface UpdateRolePrivilege {
}

package org.bandrsoftwares.celestialdiary.security.privilege.company.employee;

import org.springframework.security.access.prepost.PreAuthorize;

import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@SuppressWarnings({"SpringElInspection", "ELValidationInJSP"})
@Inherited
@Retention(RetentionPolicy.RUNTIME)
@PreAuthorize("hasAnyAuthority('company:' + #idCompany + ':employee:assign_to', 'company:' + #idCompany + ':employee:all', 'company:' + #idCompany + ':all')")
public @interface AssignEmployeePrivilege {
}

package org.bandrsoftwares.celestialdiary.security.privilege.company.equipment;

import org.springframework.security.access.prepost.PreAuthorize;

import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@SuppressWarnings({"SpringElInspection", "ELValidationInJSP"})
@Inherited
@Retention(RetentionPolicy.RUNTIME)
@PreAuthorize("hasAnyAuthority('company:' + #idCompany + ':equipment:all', 'company:' + #idCompany + ':all')")
public @interface AllEquipmentPrivilege {
}

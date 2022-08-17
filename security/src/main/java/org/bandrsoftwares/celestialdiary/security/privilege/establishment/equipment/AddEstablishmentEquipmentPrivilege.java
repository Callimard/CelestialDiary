package org.bandrsoftwares.celestialdiary.security.privilege.establishment.equipment;

import org.springframework.security.access.prepost.PreAuthorize;

import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@SuppressWarnings({"SpringElInspection", "ELValidationInJSP"})
@Inherited
@Retention(RetentionPolicy.RUNTIME)
@PreAuthorize("hasAnyAuthority('company:' + #idCompany + ':establishment:' + #idEstablishment + ':equipment:add'," +
        "'company:' + #idCompany + ':establishment:' + #idEstablishment + ':equipment:all', " +
        "'company:' + #idCompany + ':establishment:' + #idEstablishment + ':all'," +
        "'company:' + #idCompany + ':all')")
public @interface AddEstablishmentEquipmentPrivilege {
}

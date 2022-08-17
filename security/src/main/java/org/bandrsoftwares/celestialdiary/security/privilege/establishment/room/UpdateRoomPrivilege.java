package org.bandrsoftwares.celestialdiary.security.privilege.establishment.room;

import org.springframework.security.access.prepost.PreAuthorize;

import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@SuppressWarnings({"SpringElInspection", "ELValidationInJSP"})
@Inherited
@Retention(RetentionPolicy.RUNTIME)
@PreAuthorize("hasAnyAuthority('company:' + #idCompany + ':establishment:' + #idEstablishment + ':room:update'," +
        "'company:' + #idCompany + ':establishment:' + #idEstablishment + ':room:all', " +
        "'company:' + #idCompany + ':establishment:' + #idEstablishment + ':all'," +
        "'company:' + #idCompany + ':all')")
public @interface UpdateRoomPrivilege {
}

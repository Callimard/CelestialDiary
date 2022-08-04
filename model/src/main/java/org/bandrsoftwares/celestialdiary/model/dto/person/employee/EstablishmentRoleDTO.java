package org.bandrsoftwares.celestialdiary.model.dto.person.employee;

import org.bandrsoftwares.celestialdiary.model.mongodb.person.employee.EstablishmentRole;
import org.bandrsoftwares.celestialdiary.security.privilege.dto.PrivilegeDTO;

import java.util.List;

public record EstablishmentRoleDTO(String establishmentId, List<PrivilegeDTO> establishmentPrivileges) {

    public EstablishmentRoleDTO(EstablishmentRole establishmentRole) {
        this(establishmentRole.getEstablishment().getId(),
             establishmentRole.getEstablishmentPrivileges().stream().map(PrivilegeDTO::new).toList());
    }
}

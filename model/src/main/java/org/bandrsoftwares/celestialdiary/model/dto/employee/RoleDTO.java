package org.bandrsoftwares.celestialdiary.model.dto.employee;

import org.bandrsoftwares.celestialdiary.model.mongodb.employee.Role;
import org.bandrsoftwares.celestialdiary.security.privilege.dto.PrivilegeDTO;

import java.util.List;

public record RoleDTO(String id, String name, String description,
                      List<PrivilegeDTO> companyPrivileges,
                      List<EstablishmentRoleDTO> establishmentRoles) {

    public RoleDTO(Role role) {
        this(role.getId(), role.getName(), role.getDescription(),
             role.getCompanyPrivileges().stream().map(PrivilegeDTO::new).toList(),
             role.getEstablishmentRoles().stream().map(EstablishmentRoleDTO::new).toList());
    }
}

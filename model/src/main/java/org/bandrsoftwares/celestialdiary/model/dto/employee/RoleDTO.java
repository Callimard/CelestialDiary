package org.bandrsoftwares.celestialdiary.model.dto.employee;

import org.bandrsoftwares.celestialdiary.model.dto.establishment.WrappedEstablishmentDTO;
import org.bandrsoftwares.celestialdiary.model.mongodb.employee.Role;

import java.util.List;

public record RoleDTO(String id, String name, String description, List<PrivilegeDTO> privileges, WrappedEstablishmentDTO establishment) {

    public RoleDTO(Role role) {
        this(role.getId(), role.getName(), role.getDescription(),
             role.getPrivileges().stream().map(PrivilegeDTO::new).toList(),
             new WrappedEstablishmentDTO(role.getEstablishment()));
    }
}

package org.bandrsoftwares.celestialdiary.model.dto.employee;

import org.bandrsoftwares.celestialdiary.model.mongodb.employee.Privilege;

public record PrivilegeDTO(String identifierName, String name, String description) {

    public PrivilegeDTO(Privilege privilege) {
        this(privilege.getIdentifierName(), privilege.getName(), privilege.getDescription());
    }

    public Privilege toPrivilege() {
        return Privilege.builder()
                .identifierName(identifierName)
                .name(name)
                .description(description)
                .build();

    }
}

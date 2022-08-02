package org.bandrsoftwares.celestialdiary.security.privilege.dto;

import org.bandrsoftwares.celestialdiary.security.privilege.Privilege;

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

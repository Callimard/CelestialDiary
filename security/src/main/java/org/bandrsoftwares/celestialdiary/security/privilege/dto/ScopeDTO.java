package org.bandrsoftwares.celestialdiary.security.privilege.dto;

import org.bandrsoftwares.celestialdiary.security.privilege.Privilege;
import org.bandrsoftwares.celestialdiary.security.privilege.Scope;

import java.util.Arrays;
import java.util.List;

public record ScopeDTO(String name, String description, List<PrivilegeDTO> privileges, List<ScopeDTO> scopeChildren) {

    public ScopeDTO(Scope scope) {
        this(scope.getName(),
             scope.getDescription(),
             Arrays.stream(scope.allScopePrivileges()).map(scopePrivilege -> new PrivilegeDTO(Privilege.of(scopePrivilege))).toList(),
             scope.getScopeChildren().stream().map(ScopeDTO::new).toList());
    }
}

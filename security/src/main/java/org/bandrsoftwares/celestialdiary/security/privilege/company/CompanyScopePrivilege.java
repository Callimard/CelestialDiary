package org.bandrsoftwares.celestialdiary.security.privilege.company;

import org.bandrsoftwares.celestialdiary.security.privilege.Scope;

public class CompanyScopePrivilege extends Scope.ScopePrivilege {

    // Constructors.

    public CompanyScopePrivilege(String identifierName, String authorityPattern, String privilegeName, String privilegeDescription) {
        super(identifierName, authorityPattern, privilegeName, privilegeDescription);
    }

    // Methods.

    public String formatPrivilege(String companyId) {
        return getAuthorityPattern().formatted(companyId);
    }
}

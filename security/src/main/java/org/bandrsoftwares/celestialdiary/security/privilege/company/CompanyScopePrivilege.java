package org.bandrsoftwares.celestialdiary.security.privilege.company;

import org.bandrsoftwares.celestialdiary.security.privilege.Scope;

public class CompanyScopePrivilege extends Scope.ScopePrivilege {

    // Constructors.

    public CompanyScopePrivilege(String authorityPattern, String privilegeName, String privilegeDescription) {
        super(authorityPattern, privilegeName, privilegeDescription);
    }

    // Methods.

    public String formatPrivilege(String companyId) {
        return getAuthorityPattern().formatted(companyId);
    }
}

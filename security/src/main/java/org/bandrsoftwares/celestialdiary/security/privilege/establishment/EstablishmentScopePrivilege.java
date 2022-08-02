package org.bandrsoftwares.celestialdiary.security.privilege.establishment;

import org.bandrsoftwares.celestialdiary.security.privilege.Scope;

public class EstablishmentScopePrivilege extends Scope.ScopePrivilege {

    // Constructors.

    public EstablishmentScopePrivilege(String authorityPattern, String privilegeName, String privilegeDescription) {
        super(authorityPattern, privilegeName, privilegeDescription);
    }

    // Methods.

    public String formatPrivilege(String companyId, String establishmentId) {
        return getAuthorityPattern().formatted(companyId, establishmentId);
    }
}

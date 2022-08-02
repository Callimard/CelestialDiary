package org.bandrsoftwares.celestialdiary.security.privilege;

import lombok.*;
import org.bandrsoftwares.celestialdiary.security.privilege.company.CompanyManagementScope;
import org.bandrsoftwares.celestialdiary.security.privilege.company.CompanyScopePrivilege;
import org.bandrsoftwares.celestialdiary.security.privilege.establishment.EstablishmentManagementScope;
import org.bandrsoftwares.celestialdiary.security.privilege.establishment.EstablishmentScopePrivilege;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Privilege {

    // Constants.

    public static final CompanyManagementScope COMPANY_SCOPE_PRIVILEGE = new CompanyManagementScope();
    public static final EstablishmentManagementScope ESTABLISHMENT_MANAGEMENT_SCOPE = new EstablishmentManagementScope();

    // Variables.

    private String identifierName;
    private String name;
    private String description;

    // Static methods.

    public static Privilege of(Scope.ScopePrivilege scopePrivilege) {
        return new Privilege(scopePrivilege.getIdentifierName(), scopePrivilege.getPrivilegeName(), scopePrivilege.getPrivilegeDescription());
    }

    public static CompanyScopePrivilege extractCompanyPrivilege(String identifierName) {
        try {
            return COMPANY_SCOPE_PRIVILEGE.scopePrivilegeOf(identifierName);
        } catch (IllegalArgumentException e) {
            return null;
        }
    }

    public static EstablishmentScopePrivilege extractEstablishmentPrivilege(String identifierName) {
        try {
            return ESTABLISHMENT_MANAGEMENT_SCOPE.scopePrivilegeOf(identifierName);
        } catch (IllegalArgumentException e) {
            return null;
        }
    }

    public static Privilege extractPrivilegeFromIdentifier(String identifierName) {
        CompanyScopePrivilege companyScopePrivilege = extractCompanyPrivilege(identifierName);
        EstablishmentScopePrivilege establishmentPrivilege = extractEstablishmentPrivilege(identifierName);

        if (companyScopePrivilege != null) {
            return Privilege.of(companyScopePrivilege);
        } else if (establishmentPrivilege != null) {
            return Privilege.of(establishmentPrivilege);
        } else {
            return null;
        }
    }
}

package org.bandrsoftwares.celestialdiary.security.privilege.company.establishment;

import com.google.common.collect.Lists;
import org.bandrsoftwares.celestialdiary.security.privilege.PrivilegeEnum;
import org.bandrsoftwares.celestialdiary.security.privilege.Scope;
import org.bandrsoftwares.celestialdiary.security.privilege.company.CompanyScopePrivilege;

import java.util.Arrays;
import java.util.List;

public class CompanyEstablishmentManagementScope extends Scope {

    // Constructors.

    public CompanyEstablishmentManagementScope() {
        super("privilege.company.establishment.title", "privilege.company.establishment.description");
    }

    // Methods.

    @Override
    public ScopePrivilege[] allScopePrivileges() {
        return CompanyEstablishmentManagementPrivilege.allScopePrivileges();
    }

    @Override
    public List<String> attachedTags() {
        return Arrays.stream(CompanyEstablishmentManagementPrivilege.values()).map(CompanyEstablishmentManagementPrivilege::name).toList();
    }

    @Override
    protected ScopePrivilege internScopePrivilegeOf(String identifier) {
        try {
            return CompanyEstablishmentManagementPrivilege.valueOf(identifier).getScopePrivilege();
        } catch (IllegalArgumentException e) {
            return null;
        }
    }

    // Private enum.

    public enum CompanyEstablishmentManagementPrivilege implements PrivilegeEnum<CompanyScopePrivilege> {
        ESTABLISHMENT_ALL("company:%s:establishment:all", "privilege.company.establishment.all",
                          "privilege.company.establishment.all-description"),
        ESTABLISHMENT_READ("company:%s:establishment:read", "privilege.company.establishment.read",
                           "privilege.company.establishment.read-description"),
        ESTABLISHMENT_CREATE("company:%s:establishment:create", "privilege.company.establishment.create",
                             "privilege.company.establishment.create-description"),
        ESTABLISHMENT_UPDATE("company:%s:establishment:update", "privilege.company.establishment.update",
                             "privilege.company.establishment.update-description"),
        ESTABLISHMENT_ACTIVATE("company:%s:establishment:activate", "privilege.company.establishment.activate",
                               "privilege.company.establishment.activate-description");

        // Variables.

        private final String authorityPattern;
        private final String privilegeName;
        private final String privilegeDescription;

        // Constructors.

        CompanyEstablishmentManagementPrivilege(String authorityPattern, String privilegeName, String privilegeDescription) {
            this.authorityPattern = authorityPattern;
            this.privilegeName = privilegeName;
            this.privilegeDescription = privilegeDescription;
        }

        // Methods.

        public String getAuthorityPattern() {
            return authorityPattern;
        }

        public String getPrivilegeName() {
            return privilegeName;
        }

        public String getPrivilegeDescription() {
            return privilegeDescription;
        }

        @Override
        public List<PrivilegeEnum<CompanyScopePrivilege>> allValues() {
            return Lists.newArrayList(CompanyEstablishmentManagementPrivilege.values());
        }

        @Override
        public CompanyScopePrivilege getScopePrivilege() {
            return new CompanyScopePrivilege(name(), authorityPattern, privilegeName, privilegeDescription);
        }

        static ScopePrivilege[] allScopePrivileges() {
            CompanyEstablishmentManagementPrivilege[] values = CompanyEstablishmentManagementPrivilege.values();
            Scope.ScopePrivilege[] scopePrivileges = new Scope.ScopePrivilege[values.length];

            int i = 0;
            for (CompanyEstablishmentManagementPrivilege companyEstablishmentManagementPrivilege : values) {
                scopePrivileges[i] = companyEstablishmentManagementPrivilege.getScopePrivilege();
                i++;
            }

            return scopePrivileges;
        }
    }
}

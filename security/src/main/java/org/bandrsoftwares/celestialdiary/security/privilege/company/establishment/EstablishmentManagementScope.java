package org.bandrsoftwares.celestialdiary.security.privilege.company.establishment;

import com.google.common.collect.Lists;
import org.bandrsoftwares.celestialdiary.security.privilege.PrivilegeEnum;
import org.bandrsoftwares.celestialdiary.security.privilege.Scope;
import org.bandrsoftwares.celestialdiary.security.privilege.company.CompanyScopePrivilege;
import org.bandrsoftwares.celestialdiary.security.privilege.establishment.EstablishmentScopePrivilege;

import java.util.Arrays;
import java.util.List;

public class EstablishmentManagementScope extends Scope {

    // Constructors.

    public EstablishmentManagementScope() {
        super("privilege.establishment.title", "privilege.establishment.description");
    }

    // Methods.

    @Override
    public ScopePrivilege[] allScopePrivileges() {
        return EstablishmentManagementPrivilege.allScopePrivileges();
    }

    @Override
    public List<String> attachedTags() {
        return Arrays.stream(EstablishmentManagementPrivilege.values()).map(EstablishmentManagementPrivilege::name).toList();
    }

    @Override
    protected ScopePrivilege internScopePrivilegeOf(String identifier) {
        try {
            return EstablishmentManagementPrivilege.valueOf(identifier).getScopePrivilege();
        } catch (IllegalArgumentException e) {
            return null;
        }
    }

    @Override
    public EstablishmentScopePrivilege scopePrivilegeOf(String identifier) {
        return (EstablishmentScopePrivilege) super.scopePrivilegeOf(identifier);
    }

    // Private enum.

    public enum EstablishmentManagementPrivilege implements PrivilegeEnum<CompanyScopePrivilege> {
        ESTABLISHMENT_ALL(new CompanyScopePrivilege("company:%s:establishment:all", "privilege.company.establishment.all",
                                                    "privilege.company.establishment.all-description")),
        ESTABLISHMENT_READ(new CompanyScopePrivilege("company:%s:establishment:read", "privilege.company.establishment.read",
                                                     "privilege.company.establishment.read-description")),
        ESTABLISHMENT_CREATE(new CompanyScopePrivilege("company:%s:establishment:create", "privilege.company.establishment.create",
                                                       "privilege.company.establishment.create-description")),
        ESTABLISHMENT_UPDATE(new CompanyScopePrivilege("company:%s:establishment:update", "privilege.company.establishment.update",
                                                       "privilege.company.establishment.update-description")),
        ESTABLISHMENT_ACTIVATE(new CompanyScopePrivilege("company:%s:establishment:activate", "privilege.company.establishment.activate",
                                                         "privilege.company.establishment.activate-description"));

        // Variables.

        private final CompanyScopePrivilege scopePrivilege;

        // Constructors.

        EstablishmentManagementPrivilege(CompanyScopePrivilege scopePrivilege) {
            this.scopePrivilege = scopePrivilege;
        }

        // Methods.

        @Override
        public List<PrivilegeEnum<CompanyScopePrivilege>> allValues() {
            return Lists.newArrayList(EstablishmentManagementPrivilege.values());
        }

        @Override
        public CompanyScopePrivilege getScopePrivilege() {
            return scopePrivilege;
        }

        static ScopePrivilege[] allScopePrivileges() {
            EstablishmentManagementPrivilege[] values = EstablishmentManagementPrivilege.values();
            Scope.ScopePrivilege[] scopePrivileges = new Scope.ScopePrivilege[values.length];

            int i = 0;
            for (EstablishmentManagementPrivilege establishmentManagementPrivilege : values) {
                scopePrivileges[i] = establishmentManagementPrivilege.getScopePrivilege();
                i++;
            }

            return scopePrivileges;
        }
    }
}

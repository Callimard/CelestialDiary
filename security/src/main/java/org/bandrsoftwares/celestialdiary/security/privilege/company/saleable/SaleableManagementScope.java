package org.bandrsoftwares.celestialdiary.security.privilege.company.saleable;

import com.google.common.collect.Lists;
import org.bandrsoftwares.celestialdiary.security.privilege.PrivilegeEnum;
import org.bandrsoftwares.celestialdiary.security.privilege.Scope;
import org.bandrsoftwares.celestialdiary.security.privilege.company.CompanyScopePrivilege;

import java.util.Arrays;
import java.util.List;

public class SaleableManagementScope extends Scope {

    // Constructors.

    public SaleableManagementScope() {
        super("privilege.saleable.title", "privilege.saleable.description");
    }

    // Methods.

    @Override
    public ScopePrivilege[] allScopePrivileges() {
        return SaleableManagementPrivilege.allScopePrivileges();
    }

    @Override
    public List<String> attachedTags() {
        return Arrays.stream(SaleableManagementPrivilege.values()).map(SaleableManagementPrivilege::name).toList();
    }

    @Override
    protected ScopePrivilege internScopePrivilegeOf(String identifier) {
        try {
            return SaleableManagementPrivilege.valueOf(identifier).getScopePrivilege();
        } catch (IllegalArgumentException e) {
            return null;
        }
    }
// Inner enum.

    public enum SaleableManagementPrivilege implements PrivilegeEnum<CompanyScopePrivilege> {
        SALEABLE_ALL(
                new CompanyScopePrivilege("company:%s:saleable:all", "privilege.company.saleable.all", "privilege.company.saleable.all-description")),
        SALEABLE_READ(new CompanyScopePrivilege("company:%s:saleable:read", "privilege.company.saleable.read",
                                                "privilege.company.saleable.read-description")),
        SALEABLE_CREATE(new CompanyScopePrivilege("company:%s:saleable:create", "privilege.company.saleable.create",
                                                  "privilege.company.saleable.create-description")),
        SALEABLE_UPDATE(new CompanyScopePrivilege("company:%s:saleable:update", "privilege.company.saleable.update",
                                                  "privilege.company.saleable.update-description")),
        SALEABLE_ACTIVATE(new CompanyScopePrivilege("company:%s:saleable:activate", "privilege.company.saleable.activate",
                                                    "privilege.company.saleable.activate-description"));

        // Variables.

        private final CompanyScopePrivilege scopePrivilege;

        // Constructors.

        SaleableManagementPrivilege(CompanyScopePrivilege scopePrivilege) {
            this.scopePrivilege = scopePrivilege;
        }

        // Methods.

        @Override
        public List<PrivilegeEnum<CompanyScopePrivilege>> allValues() {
            return Lists.newArrayList(SaleableManagementPrivilege.values());
        }

        @Override
        public CompanyScopePrivilege getScopePrivilege() {
            return scopePrivilege;
        }

        static ScopePrivilege[] allScopePrivileges() {
            SaleableManagementPrivilege[] values = SaleableManagementPrivilege.values();
            Scope.ScopePrivilege[] scopePrivileges = new Scope.ScopePrivilege[values.length];

            int i = 0;
            for (SaleableManagementPrivilege saleableManagementPrivilege : values) {
                scopePrivileges[i] = saleableManagementPrivilege.getScopePrivilege();
                i++;
            }

            return scopePrivileges;
        }
    }
}

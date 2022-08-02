package org.bandrsoftwares.celestialdiary.security.privilege.company.role;

import com.google.common.collect.Lists;
import org.bandrsoftwares.celestialdiary.security.privilege.PrivilegeEnum;
import org.bandrsoftwares.celestialdiary.security.privilege.Scope;
import org.bandrsoftwares.celestialdiary.security.privilege.company.CompanyScopePrivilege;

import java.util.Arrays;
import java.util.List;

public class RoleManagementScope extends Scope {

    // Constructors.

    public RoleManagementScope() {
        super("privilege.role.title", "privilege.role.description");
    }

    // Methods.

    @Override
    public ScopePrivilege[] allScopePrivileges() {
        return RoleManagementPrivilege.allScopePrivileges();
    }

    @Override
    public List<String> attachedTags() {
        return Arrays.stream(RoleManagementPrivilege.values()).map(RoleManagementPrivilege::name).toList();
    }

    @Override
    protected ScopePrivilege internScopePrivilegeOf(String identifier) {
        try {
            return RoleManagementPrivilege.valueOf(identifier).getScopePrivilege();
        } catch (IllegalArgumentException e) {
            return null;
        }
    }
// Inner enum.

    public enum RoleManagementPrivilege implements PrivilegeEnum<CompanyScopePrivilege> {
        ROLE_ALL(new CompanyScopePrivilege("company:%s:role:all", "privilege.company.role.all", "privilege.company.role.all-description")),
        ROLE_READ(new CompanyScopePrivilege("company:%s:role:read", "privilege.company.role.read", "privilege.company.role.read-description")),
        ROLE_CREATE(
                new CompanyScopePrivilege("company:%s:role:create", "privilege.company.role.create", "privilege.company.role.create-description")),
        ROLE_UPDATE(
                new CompanyScopePrivilege("company:%s:role:update", "privilege.company.role.update", "privilege.company.role.update-description")),
        ROLE_DELETE(
                new CompanyScopePrivilege("company:%s:role:delete", "privilege.company.role.delete", "privilege.company.role.delete-description"));

        // Variables.

        private final CompanyScopePrivilege scopePrivilege;

        // Constructors.

        RoleManagementPrivilege(CompanyScopePrivilege scopePrivilege) {
            this.scopePrivilege = scopePrivilege;
        }

        // Methods.

        @Override
        public List<PrivilegeEnum<CompanyScopePrivilege>> allValues() {
            return Lists.newArrayList(RoleManagementPrivilege.values());
        }

        @Override
        public CompanyScopePrivilege getScopePrivilege() {
            return scopePrivilege;
        }

        static ScopePrivilege[] allScopePrivileges() {
            RoleManagementPrivilege[] values = RoleManagementPrivilege.values();
            Scope.ScopePrivilege[] scopePrivileges = new Scope.ScopePrivilege[values.length];

            int i = 0;
            for (RoleManagementPrivilege roleManagementPrivilege : values) {
                scopePrivileges[i] = roleManagementPrivilege.getScopePrivilege();
                i++;
            }

            return scopePrivileges;
        }
    }
}

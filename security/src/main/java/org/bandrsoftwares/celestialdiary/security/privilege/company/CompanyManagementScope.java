package org.bandrsoftwares.celestialdiary.security.privilege.company;

import com.google.common.collect.Lists;
import org.bandrsoftwares.celestialdiary.security.privilege.PrivilegeEnum;
import org.bandrsoftwares.celestialdiary.security.privilege.Scope;
import org.bandrsoftwares.celestialdiary.security.privilege.company.employee.EmployeeManagementScope;
import org.bandrsoftwares.celestialdiary.security.privilege.company.equipment.EquipmentManagementScope;
import org.bandrsoftwares.celestialdiary.security.privilege.company.establishment.EstablishmentManagementScope;
import org.bandrsoftwares.celestialdiary.security.privilege.company.role.RoleManagementScope;
import org.bandrsoftwares.celestialdiary.security.privilege.company.saleable.SaleableManagementScope;

import java.util.Arrays;
import java.util.List;

public class CompanyManagementScope extends Scope {

    // Constructors.

    public CompanyManagementScope() {
        super("privilege.company.title", "privilege.company.description",
              Lists.newArrayList(new EmployeeManagementScope(), new EstablishmentManagementScope(), new SaleableManagementScope(),
                                 new EquipmentManagementScope(), new RoleManagementScope()));
    }

    // Methods.

    @Override
    public ScopePrivilege[] allScopePrivileges() {
        return CompanyManagementPrivilege.allScopePrivileges();
    }

    @Override
    public List<String> attachedTags() {
        return Arrays.stream(CompanyManagementPrivilege.values()).map(CompanyManagementPrivilege::name).toList();
    }

    @Override
    protected ScopePrivilege internScopePrivilegeOf(String identifier) {
        try {
            return CompanyManagementPrivilege.valueOf(identifier).getScopePrivilege();
        } catch (IllegalArgumentException e) {
            return null;
        }
    }

    @Override
    public CompanyScopePrivilege scopePrivilegeOf(String identifier) {
        return (CompanyScopePrivilege) super.scopePrivilegeOf(identifier);
    }
// Inner enum.

    public enum CompanyManagementPrivilege implements PrivilegeEnum<CompanyScopePrivilege> {
        COMPANY_ALL(new CompanyScopePrivilege("company:%s:all", "privilege.company.all", "privilege.company.all-description"));

        // Variables.

        private final CompanyScopePrivilege scopePrivilege;

        // Constructors.

        CompanyManagementPrivilege(CompanyScopePrivilege scopePrivilege) {
            this.scopePrivilege = scopePrivilege;
        }

        // Methods.

        @Override
        public List<PrivilegeEnum<CompanyScopePrivilege>> allValues() {
            return Lists.newArrayList(CompanyManagementPrivilege.values());
        }

        @Override
        public CompanyScopePrivilege getScopePrivilege() {
            return scopePrivilege;
        }

        static ScopePrivilege[] allScopePrivileges() {
            CompanyManagementPrivilege[] values = CompanyManagementPrivilege.values();
            Scope.ScopePrivilege[] scopePrivileges = new Scope.ScopePrivilege[values.length];

            int i = 0;
            for (CompanyManagementPrivilege companyManagementPrivilege : values) {
                scopePrivileges[i] = companyManagementPrivilege.getScopePrivilege();
                i++;
            }

            return scopePrivileges;
        }
    }
}

package org.bandrsoftwares.celestialdiary.security.privilege.company.employee;

import com.google.common.collect.Lists;
import org.bandrsoftwares.celestialdiary.security.privilege.PrivilegeEnum;
import org.bandrsoftwares.celestialdiary.security.privilege.Scope;
import org.bandrsoftwares.celestialdiary.security.privilege.company.CompanyScopePrivilege;

import java.util.Arrays;
import java.util.List;

public class EmployeeManagementScope extends Scope {

    // Constants.

    // Constructors.

    public EmployeeManagementScope() {
        super("privilege.employee.title", "privilege.employee.description");
    }

    // Methods.

    @Override
    public ScopePrivilege[] allScopePrivileges() {
        return EmployeeManagementPrivilege.allScopePrivileges();
    }

    @Override
    public List<String> attachedTags() {
        return Arrays.stream(EmployeeManagementPrivilege.values()).map(EmployeeManagementPrivilege::name).toList();
    }

    @Override
    protected ScopePrivilege internScopePrivilegeOf(String identifier) {
        try {
            return EmployeeManagementPrivilege.valueOf(identifier).getScopePrivilege();
        } catch (IllegalArgumentException e) {
            return null;
        }
    }

    // Inner enum.

    public enum EmployeeManagementPrivilege implements PrivilegeEnum<CompanyScopePrivilege> {
        EMPLOYEE_ALL(new CompanyScopePrivilege("company:%s:employee:all", "privilege.company.employee.all",
                                               "privilege.company.employee.all-description")),
        EMPLOYEE_READ(new CompanyScopePrivilege("company:%s:employee:read", "privilege.company.employee.read",
                                                "privilege.company.employee.read-description")),
        EMPLOYEE_CREATE(new CompanyScopePrivilege("company:%s:employee:create", "privilege.company.employee.create",
                                                  "privilege.company.employee.create-description")),
        EMPLOYEE_UPDATE_INFORMATION(new CompanyScopePrivilege("company:%s:employee:update", "privilege.company.employee.update",
                                                              "privilege.company.employee.update-description")),
        EMPLOYEE_UPDATE_ROLE(new CompanyScopePrivilege("company:%s:employee:update_role", "privilege.company.employee.update-role",
                                                       "privilege.company.employee.update-role-description")),
        EMPLOYEE_ASSIGN_TO(new CompanyScopePrivilege("company:%s:employee:assign_to", "privilege.company.employee.assign-to",
                                                     "privilege.company.employee.assign-to-description")),
        EMPLOYEE_ACTIVATE(new CompanyScopePrivilege("company:%s:employee:activate", "privilege.company.employee.activate",
                                                    "privilege.company.employee.activate-description"));

        // Variables.

        private final CompanyScopePrivilege scopePrivilege;

        // Constructors.

        EmployeeManagementPrivilege(CompanyScopePrivilege scopePrivilege) {
            this.scopePrivilege = scopePrivilege;
        }

        // Methods.

        @Override
        public List<PrivilegeEnum<CompanyScopePrivilege>> allValues() {
            return Lists.newArrayList(EmployeeManagementPrivilege.values());
        }

        @Override
        public CompanyScopePrivilege getScopePrivilege() {
            return scopePrivilege;
        }

        static ScopePrivilege[] allScopePrivileges() {
            EmployeeManagementPrivilege[] values = EmployeeManagementPrivilege.values();
            Scope.ScopePrivilege[] scopePrivileges = new Scope.ScopePrivilege[values.length];

            int i = 0;
            for (EmployeeManagementPrivilege employeePrivilege : values) {
                scopePrivileges[i] = employeePrivilege.getScopePrivilege();
                i++;
            }

            return scopePrivileges;
        }
    }
}

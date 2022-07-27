package org.bandrsoftwares.celestialdiary.security.privilege.company;

import org.bandrsoftwares.celestialdiary.security.privilege.PrivilegeEnum;

public enum CompanyManagementPrivilege implements PrivilegeEnum {
    COMPANY_ALL("company:%s:all", "privilege.company.all", "privilege.company.all-description"),

    // Employee Management Privileges

    EMPLOYEE_ALL("company:%s:employee:all", "privilege.company.employee.all", "privilege.company.employee.all-description"),
    EMPLOYEE_READ("company:%s:employee:read", "privilege.company.employee.read", "privilege.company.employee.read-description"),
    EMPLOYEE_CREATE("company:%s:employee:create", "privilege.company.employee.create", "privilege.company.employee.create-description"),
    EMPLOYEE_UPDATE_INFORMATION("company:%s:employee:update", "privilege.company.employee.update", "privilege.company.employee.update-description"),
    EMPLOYEE_UPDATE_ROLE("company:%s:employee:update_role", "privilege.company.employee.update-role",
                         "privilege.company.employee.update-role-description"),
    EMPLOYEE_ASSIGN_TO("company:%s:employee:assign_to", "privilege.company.employee.assign-to", "privilege.company.employee.assign-to-description"),
    EMPLOYEE_ACTIVATE("company:%s:employee:activate", "privilege.company.employee.activate", "privilege.company.employee.activate-description"),

    // Establishment Management Privileges

    ESTABLISHMENT_ALL("company:%s:establishment:all", "privilege.company.establishment.all", "privilege.company.establishment.all-description"),
    ESTABLISHMENT_READ("company:%s:establishment:read", "privilege.company.establishment.read", "privilege.company.establishment.read-description"),
    ESTABLISHMENT_CREATE("company:%s:establishment:create", "privilege.company.establishment.create",
                         "privilege.company.establishment.create-description"),
    ESTABLISHMENT_UPDATE("company:%s:establishment:update", "privilege.company.establishment.update",
                         "privilege.company.establishment.update-description"),
    ESTABLISHMENT_ACTIVATE("company:%s:establishment:activate", "privilege.company.establishment.activate",
                           "privilege.company.establishment.activate-description"),

    // Saleable Management Privileges

    SALEABLE_ALL("company:%s:saleable:all", "privilege.company.saleable.all", "privilege.company.saleable.all-description"),
    SALEABLE_READ("company:%s:saleable:read", "privilege.company.saleable.read", "privilege.company.saleable.read-description"),
    SALEABLE_CREATE("company:%s:saleable:create", "privilege.company.saleable.create", "privilege.company.saleable.create-description"),
    SALEABLE_UPDATE("company:%s:saleable:update", "privilege.company.saleable.update", "privilege.company.saleable.update-description"),
    SALEABLE_ACTIVATE("company:%s:saleable:activate", "privilege.company.saleable.activate", "privilege.company.saleable.activate-description"),

    // Role Management Privileges

    ROLE_ALL("company:%s:role:all", "privilege.company.role.all", "privilege.company.role.all-description"),
    ROLE_READ("company:%s:role:read", "privilege.company.role.read", "privilege.company.role.read-description"),
    ROLE_CREATE("company:%s:role:create", "privilege.company.role.create", "privilege.company.role.create-description"),
    ROLE_UPDATE("company:%s:role:update", "privilege.company.role.update", "privilege.company.role.update-description"),
    ROLE_DELETE("company:%s:role:delete", "privilege.company.role.delete", "privilege.company.role.delete-description");

    private final String privilegePattern;
    private final String privilegeName;
    private final String privilegeDescription;

    CompanyManagementPrivilege(String privilegePattern, String privilegeName, String privilegeDescription) {
        this.privilegePattern = privilegePattern;
        this.privilegeName = privilegeName;
        this.privilegeDescription = privilegeDescription;
    }

    public String getPrivilegeFormatted(String companyId) {
        return privilegePattern.formatted(companyId);
    }

    @Override
    public String enumName() {
        return this.name();
    }

    @Override
    public String getPrivilegeName() {
        return privilegeName;
    }

    @Override
    public String getPrivilegeDescription() {
        return privilegeDescription;
    }
}

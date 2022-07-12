package org.bandrsoftwares.celestialdiary.security.privilege.company;

public enum CompanyManagementPrivilege {
    COMPANY_ALL("company:%s:all"),

    // Employee Management Privilege

    EMPLOYEE_ALL("company:%s:employee:all"),
    EMPLOYEE_READ("company:%s:employee:read"),
    EMPLOYEE_CREATE("company:%s:employee:create"),
    EMPLOYEE_UPDATE_INFORMATION("company:%s:employee:update"),
    EMPLOYEE_UPDATE_ROLE("company:%s:employee:update_role"),
    EMPLOYEE_ASSIGN_TO("company:%s:employee:assign_to"),
    EMPLOYEE_ACTIVATE("company:%s:employee:activate"),

    // Establishment Management Privilege

    ESTABLISHMENT_ALL("company:%s:establishment:all"),
    ESTABLISHMENT_READ("company:%s:establishment:read"),
    ESTABLISHMENT_CREATE("company:%s:establishment:create"),
    ESTABLISHMENT_UPDATE("company:%s:establishment:update"),
    ESTABLISHMENT_ACTIVATE("company:%s:establishment:activate");

    private final String privilege;

    CompanyManagementPrivilege(String privilegeName) {
        this.privilege = privilegeName;
    }

    public String getPrivilege(String companyId) {
        return privilege.formatted(companyId);
    }
}

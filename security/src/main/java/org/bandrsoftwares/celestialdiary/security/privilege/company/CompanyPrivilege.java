package org.bandrsoftwares.celestialdiary.security.privilege.company;

public enum CompanyPrivilege {
    COMPANY_ALL("company:%s:all"),
    COMPANY_EMPLOYEE_ALL("company:%s:employee:all"),
    COMPANY_EMPLOYEE_READ("company:%s:employee:read"),
    COMPANY_EMPLOYEE_CREATE("company:%s:employee:create"),
    COMPANY_EMPLOYEE_UPDATE_INFORMATION("company:%s:employee:update"),
    COMPANY_EMPLOYEE_UPDATE_ROLE("company:%s:employee:update_role"),
    COMPANY_EMPLOYEE_ASSIGN_TO("company:%s:employee:assign_to"),
    COMPANY_EMPLOYEE_ACTIVATE("company:%s:employee:activate");

    private final String privilege;

    CompanyPrivilege(String privilegeName) {
        this.privilege = privilegeName;
    }

    public String getPrivilege(String companyId) {
        return privilege.formatted(companyId);
    }
}

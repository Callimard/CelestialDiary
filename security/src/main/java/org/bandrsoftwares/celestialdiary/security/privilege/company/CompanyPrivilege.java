package org.bandrsoftwares.celestialdiary.security.privilege.company;

public enum CompanyPrivilege {
    COMPANY_ALL("company:%s:all");

    private final String privilege;

    CompanyPrivilege(String privilegeName) {
        this.privilege = privilegeName;
    }

    public String getPrivilege(String companyId) {
        return privilege.formatted(companyId);
    }
}

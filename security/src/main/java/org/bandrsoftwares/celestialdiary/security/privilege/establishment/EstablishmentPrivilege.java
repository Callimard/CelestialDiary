package org.bandrsoftwares.celestialdiary.security.privilege.establishment;

public enum EstablishmentPrivilege {
    ESTABLISHMENT_ALL("company:%s:establishment:%s:all");

    private final String privilege;

    EstablishmentPrivilege(String privilegeName) {
        this.privilege = privilegeName;
    }

    public String getPrivilege(String companyId, String establishmentId) {
        return privilege.formatted(companyId, establishmentId);
    }
}

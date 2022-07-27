package org.bandrsoftwares.celestialdiary.security.privilege.establishment;

import org.bandrsoftwares.celestialdiary.security.privilege.PrivilegeEnum;

public enum EstablishmentPrivilege implements PrivilegeEnum {
    ESTABLISHMENT_ALL("company:%s:establishment:%s:all", "", "");

    private final String privilegePattern;
    private final String privilegeName;
    private final String privilegeDescription;

    EstablishmentPrivilege(String privilegePattern, String privilegeName, String privilegeDescription) {
        this.privilegePattern = privilegePattern;
        this.privilegeName = privilegeName;
        this.privilegeDescription = privilegeDescription;
    }

    public String getPrivilege(String companyId, String establishmentId) {
        return privilegePattern.formatted(companyId, establishmentId);
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

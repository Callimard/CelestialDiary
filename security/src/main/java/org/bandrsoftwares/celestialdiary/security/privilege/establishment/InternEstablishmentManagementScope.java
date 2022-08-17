package org.bandrsoftwares.celestialdiary.security.privilege.establishment;

import com.google.common.collect.Lists;
import org.bandrsoftwares.celestialdiary.security.privilege.PrivilegeEnum;
import org.bandrsoftwares.celestialdiary.security.privilege.Scope;
import org.bandrsoftwares.celestialdiary.security.privilege.establishment.equipment.EstablishmentEquipmentManagementScope;
import org.bandrsoftwares.celestialdiary.security.privilege.establishment.room.RoomManagementScope;

import java.util.Arrays;
import java.util.List;

public class InternEstablishmentManagementScope extends Scope {

    // Constructors.

    public InternEstablishmentManagementScope() {
        super("privilege.establishment.title", "privilege.establishment.description",
              Lists.newArrayList(new RoomManagementScope(), new EstablishmentEquipmentManagementScope()));
    }

    // Methods.

    @Override
    public ScopePrivilege[] allScopePrivileges() {
        return EstablishmentManagementPrivilege.allScopePrivileges();
    }

    @Override
    public List<String> attachedTags() {
        return Arrays.stream(EstablishmentManagementPrivilege.values()).map(EstablishmentManagementPrivilege::name).toList();
    }

    @Override
    protected ScopePrivilege internScopePrivilegeOf(String identifier) {
        try {
            return EstablishmentManagementPrivilege.valueOf(identifier).getScopePrivilege();
        } catch (IllegalArgumentException e) {
            return null;
        }
    }

    @Override
    public EstablishmentScopePrivilege scopePrivilegeOf(String identifier) {
        return (EstablishmentScopePrivilege) super.scopePrivilegeOf(identifier);
    }

    // Inner enum

    public enum EstablishmentManagementPrivilege implements PrivilegeEnum<EstablishmentScopePrivilege> {
        ESTABLISHMENT_ALL("company:%s:establishment:%s:all", "privilege.establishment.all",
                          "privilege.establishment.all-description");

        // Variables.

        private final String authorityPattern;
        private final String privilegeName;
        private final String privilegeDescription;

        // Constructors.

        EstablishmentManagementPrivilege(String authorityPattern, String privilegeName, String privilegeDescription) {
            this.authorityPattern = authorityPattern;
            this.privilegeName = privilegeName;
            this.privilegeDescription = privilegeDescription;
        }

        // Methods.

        public String getAuthorityPattern() {
            return authorityPattern;
        }

        public String getPrivilegeName() {
            return privilegeName;
        }

        public String getPrivilegeDescription() {
            return privilegeDescription;
        }

        @Override
        public List<PrivilegeEnum<EstablishmentScopePrivilege>> allValues() {
            return Lists.newArrayList(EstablishmentManagementPrivilege.values());
        }

        @Override
        public EstablishmentScopePrivilege getScopePrivilege() {
            return new EstablishmentScopePrivilege(name(), authorityPattern, privilegeName, privilegeDescription);
        }

        static ScopePrivilege[] allScopePrivileges() {
            EstablishmentManagementPrivilege[] values = EstablishmentManagementPrivilege.values();
            Scope.ScopePrivilege[] scopePrivileges = new Scope.ScopePrivilege[values.length];

            int i = 0;
            for (EstablishmentManagementPrivilege establishmentManagementPrivilege : values) {
                scopePrivileges[i] = establishmentManagementPrivilege.getScopePrivilege();
                i++;
            }

            return scopePrivileges;
        }
    }
}

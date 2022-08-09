package org.bandrsoftwares.celestialdiary.security.privilege.company.equipment;

import com.google.common.collect.Lists;
import org.bandrsoftwares.celestialdiary.security.privilege.PrivilegeEnum;
import org.bandrsoftwares.celestialdiary.security.privilege.Scope;
import org.bandrsoftwares.celestialdiary.security.privilege.company.CompanyScopePrivilege;

import java.util.Arrays;
import java.util.List;

public class EquipmentManagementScope extends Scope {

    // Constructors.

    public EquipmentManagementScope() {
        super("privilege.company.equipment.title", "privilege.company.equipment.description");
    }

    // Methods.

    @Override
    public ScopePrivilege[] allScopePrivileges() {
        return EquipmentManagementPrivilege.allScopePrivileges();
    }

    @Override
    public List<String> attachedTags() {
        return Arrays.stream(EquipmentManagementPrivilege.values()).map(
                EquipmentManagementPrivilege::name).toList();
    }

    @Override
    protected ScopePrivilege internScopePrivilegeOf(String identifier) {
        try {
            return EquipmentManagementPrivilege.valueOf(identifier).getScopePrivilege();
        } catch (IllegalArgumentException e) {
            return null;
        }
    }

    // Inner enum.

    public enum EquipmentManagementPrivilege implements PrivilegeEnum<CompanyScopePrivilege> {
        EQUIPMENT_ALL("company:%s:equipment:all", "privilege.company.equipment.all", "privilege.company.equipment.all-description"),
        EQUIPMENT_READ("company:%s:equipment:read", "privilege.company.equipment.read", "privilege.company.equipment.read-description"),
        EQUIPMENT_CREATE("company:%s:equipment:create", "privilege.company.equipment.create", "privilege.company.equipment.create-description"),
        EQUIPMENT_UPDATE("company:%s:equipment:update", "privilege.company.equipment.update", "privilege.company.equipment.update-description"),
        EQUIPMENT_DELETE("company:%s:equipment:delete", "privilege.company.equipment.delete", "privilege.company.equipment.delete-description");

        // Variables.

        private final String authorityPattern;
        private final String privilegeName;
        private final String privilegeDescription;

        // Constructors.

        EquipmentManagementPrivilege(String authorityPattern, String privilegeName, String privilegeDescription) {
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
        public List<PrivilegeEnum<CompanyScopePrivilege>> allValues() {
            return Lists.newArrayList(EquipmentManagementPrivilege.values());
        }

        @Override
        public CompanyScopePrivilege getScopePrivilege() {
            return new CompanyScopePrivilege(name(), authorityPattern, privilegeName, privilegeDescription);
        }

        static ScopePrivilege[] allScopePrivileges() {
            EquipmentManagementPrivilege[] values = EquipmentManagementPrivilege.values();
            Scope.ScopePrivilege[] scopePrivileges = new Scope.ScopePrivilege[values.length];

            int i = 0;
            for (EquipmentManagementPrivilege equipmentManagementPrivilege : values) {
                scopePrivileges[i] = equipmentManagementPrivilege.getScopePrivilege();
                i++;
            }

            return scopePrivileges;
        }
    }
}

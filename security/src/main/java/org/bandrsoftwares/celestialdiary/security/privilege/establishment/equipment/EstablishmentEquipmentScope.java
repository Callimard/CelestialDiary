package org.bandrsoftwares.celestialdiary.security.privilege.establishment.equipment;

import com.google.common.collect.Lists;
import org.bandrsoftwares.celestialdiary.security.privilege.PrivilegeEnum;
import org.bandrsoftwares.celestialdiary.security.privilege.Scope;
import org.bandrsoftwares.celestialdiary.security.privilege.establishment.EstablishmentScopePrivilege;

import java.util.Arrays;
import java.util.List;

public class EstablishmentEquipmentScope extends Scope {

    // Constructors.

    public EstablishmentEquipmentScope() {
        super("privilege.establishment.equipment.title", "privilege.establishment.equipment.description");
    }


    // Methods.

    @Override
    public ScopePrivilege[] allScopePrivileges() {
        return EstablishmentEquipmentPrivilege.allScopePrivileges();
    }

    @Override
    public List<String> attachedTags() {
        return Arrays.stream(EstablishmentEquipmentPrivilege.values()).map(EstablishmentEquipmentPrivilege::name).toList();
    }

    @Override
    protected ScopePrivilege internScopePrivilegeOf(String identifier) {
        try {
            return EstablishmentEquipmentPrivilege.valueOf(identifier).getScopePrivilege();
        } catch (IllegalArgumentException e) {
            return null;
        }
    }

    // Inner enum

    public enum EstablishmentEquipmentPrivilege implements PrivilegeEnum<EstablishmentScopePrivilege> {
        ESTABLISHMENT_EQUIPMENT_ALL("company:%s:establishment:%s:equipment:all", "privilege.establishment.equipment.all",
                                    "privilege.establishment.equipment.all-description"),
        ESTABLISHMENT_EQUIPMENT_READ("company:%s:establishment:%s:equipment:read", "privilege.establishment.equipment.read",
                                     "privilege.establishment.equipment.read-description"),
        ESTABLISHMENT_EQUIPMENT_ADD("company:%s:establishment:%s:equipment:add", "privilege.establishment.equipment.add",
                                    "privilege.establishment.equipment.add-description"),
        ESTABLISHMENT_EQUIPMENT_UPDATE("company:%s:establishment:%s:equipment:update", "privilege.establishment.equipment.update",
                                       "privilege.establishment.equipment.update-description"),
        ESTABLISHMENT_EQUIPMENT_DELETE("company:%s:establishment:%s:equipment:delete", "privilege.establishment.equipment.delete",
                                       "privilege.establishment.equipment.delete-description");

        // Variables.

        private final String authorityPattern;
        private final String privilegeName;
        private final String privilegeDescription;

        // Constructors.

        EstablishmentEquipmentPrivilege(String authorityPattern, String privilegeName, String privilegeDescription) {
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
            return Lists.newArrayList(EstablishmentEquipmentPrivilege.values());
        }

        @Override
        public EstablishmentScopePrivilege getScopePrivilege() {
            return new EstablishmentScopePrivilege(name(), authorityPattern, privilegeName, privilegeDescription);
        }

        static ScopePrivilege[] allScopePrivileges() {
            EstablishmentEquipmentPrivilege[] values = EstablishmentEquipmentPrivilege.values();
            Scope.ScopePrivilege[] scopePrivileges = new Scope.ScopePrivilege[values.length];

            int i = 0;
            for (EstablishmentEquipmentPrivilege establishmentEquipmentPrivilege : values) {
                scopePrivileges[i] = establishmentEquipmentPrivilege.getScopePrivilege();
                i++;
            }

            return scopePrivileges;
        }
    }
}

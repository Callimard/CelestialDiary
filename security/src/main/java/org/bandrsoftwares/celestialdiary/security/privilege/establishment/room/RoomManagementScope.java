package org.bandrsoftwares.celestialdiary.security.privilege.establishment.room;

import com.google.common.collect.Lists;
import org.bandrsoftwares.celestialdiary.security.privilege.PrivilegeEnum;
import org.bandrsoftwares.celestialdiary.security.privilege.Scope;
import org.bandrsoftwares.celestialdiary.security.privilege.establishment.EstablishmentScopePrivilege;

import java.util.Arrays;
import java.util.List;

public class RoomManagementScope extends Scope {

    // Constructors.

    public RoomManagementScope() {
        super("privilege.establishment.room.title", "privilege.establishment.room.description");
    }

    // Methods.

    @Override
    public ScopePrivilege[] allScopePrivileges() {
        return RoomManagementPrivilege.allScopePrivileges();
    }

    @Override
    public List<String> attachedTags() {
        return Arrays.stream(RoomManagementPrivilege.values()).map(RoomManagementPrivilege::name).toList();
    }

    @Override
    protected EstablishmentScopePrivilege internScopePrivilegeOf(String identifier) {
        try {
            return RoomManagementPrivilege.valueOf(identifier).getScopePrivilege();
        } catch (IllegalArgumentException e) {
            return null;
        }
    }

    // Inner enum

    public enum RoomManagementPrivilege implements PrivilegeEnum<EstablishmentScopePrivilege> {
        ROOM_ALL("company:%s:establishment:%s:room:all", "privilege.establishment.room.all",
                 "privilege.establishment.room.all-description"),
        ROOM_READ("company:%s:establishment:%s:room:read", "privilege.establishment.room.read",
                  "privilege.establishment.room.read-description"),
        ROOM_CREATE("company:%s:establishment:%s:room:create", "privilege.establishment.room.create",
                    "privilege.establishment.room.create-description"),
        ROOM_UPDATE("company:%s:establishment:%s:room:update", "privilege.establishment.room.update",
                    "privilege.establishment.room.update-description"),
        ROOM_DELETE("company:%s:establishment:%s:room:delete", "privilege.establishment.room.delete",
                    "privilege.establishment.room.delete-description");

        // Variables.

        private final String authorityPattern;
        private final String privilegeName;
        private final String privilegeDescription;

        // Constructors.

        RoomManagementPrivilege(String authorityPattern, String privilegeName, String privilegeDescription) {
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
            return Lists.newArrayList(RoomManagementPrivilege.values());
        }

        @Override
        public EstablishmentScopePrivilege getScopePrivilege() {
            return new EstablishmentScopePrivilege(name(), authorityPattern, privilegeName, privilegeDescription);
        }

        static ScopePrivilege[] allScopePrivileges() {
            RoomManagementPrivilege[] values = RoomManagementPrivilege.values();
            Scope.ScopePrivilege[] scopePrivileges = new Scope.ScopePrivilege[values.length];

            int i = 0;
            for (RoomManagementPrivilege roomPrivilege : values) {
                scopePrivileges[i] = roomPrivilege.getScopePrivilege();
                i++;
            }

            return scopePrivileges;
        }
    }
}

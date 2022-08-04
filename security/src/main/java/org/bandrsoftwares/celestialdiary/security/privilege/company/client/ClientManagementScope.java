package org.bandrsoftwares.celestialdiary.security.privilege.company.client;

import com.google.common.collect.Lists;
import org.bandrsoftwares.celestialdiary.security.privilege.PrivilegeEnum;
import org.bandrsoftwares.celestialdiary.security.privilege.Scope;
import org.bandrsoftwares.celestialdiary.security.privilege.company.CompanyScopePrivilege;

import java.util.Arrays;
import java.util.List;

public class ClientManagementScope extends Scope {

    // Constructors.

    public ClientManagementScope() {
        super("privilege.client.title", "privilege.client.description");
    }

    // Methods.

    @Override
    public ScopePrivilege[] allScopePrivileges() {
        return ClientManagementPrivilege.allScopePrivileges();
    }

    @Override
    public List<String> attachedTags() {
        return Arrays.stream(ClientManagementPrivilege.values()).map(ClientManagementPrivilege::name).toList();
    }

    @Override
    protected ScopePrivilege internScopePrivilegeOf(String identifier) {
        try {
            return ClientManagementPrivilege.valueOf(identifier).getScopePrivilege();
        } catch (IllegalArgumentException e) {
            return null;
        }
    }

    // Inner enum.

    public enum ClientManagementPrivilege implements PrivilegeEnum<CompanyScopePrivilege> {
        CLIENT_ALL("company:%s:client:all", "privilege.company.client.all", "privilege.company.client.all-description"),
        CLIENT_READ("company:%s:client:read", "privilege.company.client.read", "privilege.company.client.read-description"),
        CLIENT_CREATE("company:%s:client:create", "privilege.company.client.create", "privilege.company.client.create-description"),
        CLIENT_UPDATE("company:%s:client:update", "privilege.company.client.update", "privilege.company.client.update-description");

        // Variables.

        private final String authorityPattern;
        private final String privilegeName;
        private final String privilegeDescription;

        // Constructors.

        ClientManagementPrivilege(String authorityPattern, String privilegeName, String privilegeDescription) {
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
            return Lists.newArrayList(ClientManagementPrivilege.values());
        }

        @Override
        public CompanyScopePrivilege getScopePrivilege() {
            return new CompanyScopePrivilege(name(), authorityPattern, privilegeName, privilegeDescription);
        }

        static ScopePrivilege[] allScopePrivileges() {
            ClientManagementPrivilege[] values = ClientManagementPrivilege.values();
            Scope.ScopePrivilege[] scopePrivileges = new Scope.ScopePrivilege[values.length];

            int i = 0;
            for (ClientManagementPrivilege clientPrivilege : values) {
                scopePrivileges[i] = clientPrivilege.getScopePrivilege();
                i++;
            }

            return scopePrivileges;
        }
    }
}

package org.bandrsoftwares.celestialdiary.security.privilege;

import com.google.common.collect.Lists;
import lombok.Getter;
import lombok.NonNull;
import lombok.ToString;

import java.util.List;

@ToString
@Getter
public abstract class Scope {

    @NonNull
    private final String name;

    @NonNull
    private final String description;

    @NonNull
    private final List<Scope> scopeChildren;

    // Constructors.

    protected Scope(@NonNull String name, @NonNull String description) {
        this(name, description, Lists.newArrayList());
    }

    protected Scope(@NonNull String name, @NonNull String description, @NonNull List<Scope> scopeChildren) {
        this.name = name;
        this.description = description;
        this.scopeChildren = scopeChildren;
    }

    // Methods.

    public abstract ScopePrivilege[] allScopePrivileges();

    public abstract List<String> attachedTags();

    protected abstract ScopePrivilege internScopePrivilegeOf(String identifier);

    /**
     * @param identifier the identifier of the ScopePrivilege
     *
     * @return the ScopePrivilege corresponding to the identifier
     *
     * @throws IllegalArgumentException if the identifier is not corresponding to any ScopePrivilege
     */
    public ScopePrivilege scopePrivilegeOf(String identifier) {
        ScopePrivilege scopePrivilege = internScopePrivilegeOf(identifier);
        if (scopePrivilege == null) {
            for (Scope childScope : scopeChildren) {
                ScopePrivilege childScopePrivilege = childScope.internScopePrivilegeOf(identifier);
                if (childScopePrivilege != null) {
                    return childScopePrivilege;
                }
            }
            throw new IllegalArgumentException("The identifier " + identifier + " does not correspond to a Company Management Privilege");
        } else {
            return scopePrivilege;
        }
    }

    // Inner classes.

    public static class ScopePrivilege {

        // Variables.

        private final String authorityPattern;
        private final String privilegeName;
        private final String privilegeDescription;

        // Constructors.

        protected ScopePrivilege(String authorityPattern, String privilegeName, String privilegeDescription) {
            this.authorityPattern = authorityPattern;
            this.privilegeName = privilegeName;
            this.privilegeDescription = privilegeDescription;
        }

        // Getters.

        public String getAuthorityPattern() {
            return authorityPattern;
        }

        public String getPrivilegeName() {
            return privilegeName;
        }

        public String getPrivilegeDescription() {
            return privilegeDescription;
        }
    }
}

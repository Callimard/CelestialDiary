package org.bandrsoftwares.celestialdiary.security.privilege;

import java.util.List;

public interface PrivilegeEnum<T extends Scope.ScopePrivilege> {

    List<PrivilegeEnum<T>> allValues();

    T getScopePrivilege();
}

package org.bandrsoftwares.celestialdiary.company_management_service.service;

import org.bandrsoftwares.celestialdiary.aop.company.CompanyId;
import org.bandrsoftwares.celestialdiary.aop.employee.RoleId;
import org.bandrsoftwares.celestialdiary.model.dto.employee.EstablishmentRoleDTO;
import org.bandrsoftwares.celestialdiary.model.mongodb.employee.Role;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.List;

public interface RoleManagementService {

    List<Role> allRegisteredRole(@CompanyId String companyId);

    List<Role> searchRole(@CompanyId String companyId, String filter);

    Role getSpecificRole(@CompanyId String companyId, @RoleId String roleId);

    Role createRole(@CompanyId String companyId, @Valid RoleCreationInformation information);

    record RoleCreationInformation(@NotNull @NotBlank String name, String description, List<String> companyPrivilegeIdentifiers,
                                   List<EstablishmentRoleDTO> establishmentRoles) {
    }

    Role updateRole(@CompanyId String companyId, @RoleId String roleId, @Valid RoleUpdatedInformation updates);

    record RoleUpdatedInformation(String name, String description, List<String> companyPrivilegeIdentifiers,
                                  List<EstablishmentRoleDTO> establishmentRoles) {
    }

    void deleteRole(@CompanyId String companyId, @RoleId String roleId);
}

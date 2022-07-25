package org.bandrsoftwares.celestialdiary.company_management_service.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.bandrsoftwares.celestialdiary.company_management_service.service.RoleManagementService;
import org.bandrsoftwares.celestialdiary.model.dto.employee.RoleDTO;
import org.bandrsoftwares.celestialdiary.security.privilege.company.role.CreateRolePrivilege;
import org.bandrsoftwares.celestialdiary.security.privilege.company.role.DeleteRolePrivilege;
import org.bandrsoftwares.celestialdiary.security.privilege.company.role.ReadRolePrivilege;
import org.bandrsoftwares.celestialdiary.security.privilege.company.role.UpdateRolePrivilege;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.bandrsoftwares.celestialdiary.api.v1.ApiCompanyV1.ROLES_URL;
import static org.bandrsoftwares.celestialdiary.api.v1.ApiCompanyV1.SPECIFIC_ROLE;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping(ROLES_URL)
public class RoleManagerController {

    // Variables.

    private final RoleManagementService roleManagementService;

    // Methods.

    @ReadRolePrivilege
    @GetMapping
    public List<RoleDTO> getRoles(@PathVariable(name = "idCompany") String idCompany,
                                  @RequestParam(name = "filter", required = false) String filter) {
        if (filter == null) {
            return roleManagementService.allRegisteredRole(idCompany).stream().map(RoleDTO::new).toList();
        } else {
            return roleManagementService.searchRole(idCompany, filter).stream().map(RoleDTO::new).toList();
        }
    }

    @CreateRolePrivilege
    @PostMapping
    public RoleDTO createRole(@PathVariable(name = "idCompany") String idCompany,
                              @RequestBody RoleManagementService.RoleCreationInformation information) {
        return new RoleDTO(roleManagementService.createRole(idCompany, information));
    }

    @ReadRolePrivilege
    @GetMapping(SPECIFIC_ROLE)
    public RoleDTO getSpecificRole(@PathVariable(name = "idCompany") String idCompany,
                                   @PathVariable(name = "idRole") String idRole) {
        return new RoleDTO(roleManagementService.getSpecificRole(idCompany, idRole));
    }

    @UpdateRolePrivilege
    @PutMapping(SPECIFIC_ROLE)
    public RoleDTO updateRole(@PathVariable(name = "idCompany") String idCompany,
                              @PathVariable(name = "idRole") String idRole,
                              @RequestBody RoleManagementService.RoleUpdatedInformation updates) {
        return new RoleDTO(roleManagementService.updateRole(idCompany, idRole, updates));
    }

    @DeleteRolePrivilege
    @DeleteMapping(SPECIFIC_ROLE)
    public void deleteRole(@PathVariable(name = "idCompany") String idCompany,
                           @PathVariable(name = "idRole") String idRole) {
        roleManagementService.deleteRole(idCompany, idRole);
    }
}

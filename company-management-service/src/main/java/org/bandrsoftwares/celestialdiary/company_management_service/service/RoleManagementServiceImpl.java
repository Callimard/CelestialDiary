package org.bandrsoftwares.celestialdiary.company_management_service.service;

import com.google.common.collect.Lists;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.bandrsoftwares.celestialdiary.aop.SearchingAspect;
import org.bandrsoftwares.celestialdiary.aop.company.CheckCompanyCoherence;
import org.bandrsoftwares.celestialdiary.aop.company.CompanyId;
import org.bandrsoftwares.celestialdiary.aop.company.SearchCompany;
import org.bandrsoftwares.celestialdiary.aop.employee.RoleId;
import org.bandrsoftwares.celestialdiary.aop.employee.SearchRole;
import org.bandrsoftwares.celestialdiary.model.dto.employee.PrivilegeDTO;
import org.bandrsoftwares.celestialdiary.model.mongodb.company.Company;
import org.bandrsoftwares.celestialdiary.model.mongodb.employee.Role;
import org.bandrsoftwares.celestialdiary.model.mongodb.employee.RoleRepository;
import org.bandrsoftwares.celestialdiary.model.mongodb.establishment.Establishment;
import org.bandrsoftwares.celestialdiary.model.mongodb.establishment.EstablishmentRepository;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@Slf4j
@RequiredArgsConstructor
@Validated
@Service
public class RoleManagementServiceImpl implements RoleManagementService {

    // Variables.

    private final RoleRepository roleRepository;
    private final EstablishmentRepository establishmentRepository;

    // Methods.

    @SearchCompany
    @Override
    public List<Role> allRegisteredRole(@CompanyId String companyId) {
        return roleRepository.findByCompany(SearchingAspect.COMPANY_FOUND.get());
    }

    @SearchCompany
    @Override
    public List<Role> searchRole(@CompanyId String companyId, String filter) {
        String regexFilter = ".*" + filter + ".*";
        return roleRepository.findByCompanyAndNameRegex(SearchingAspect.COMPANY_FOUND.get(), regexFilter);
    }

    @SearchRole
    @CheckCompanyCoherence
    @Override
    public Role getSpecificRole(@CompanyId String companyId, @RoleId String roleId) {
        return SearchingAspect.ROLE_FOUND.get();
    }

    @SearchCompany
    @Override
    public Role createRole(@CompanyId String companyId, @Valid RoleCreationInformation information) {
        Role role = createRoleForm(SearchingAspect.COMPANY_FOUND.get(), information);
        return roleRepository.insert(role);
    }

    private Role createRoleForm(Company company, RoleCreationInformation info) {
        Establishment establishment = establishmentRepository.findByCompanyAndId(company, info.establishmentId());
        return Role.builder()
                .name(info.name())
                .description(info.description())
                .privileges(info.privileges().stream().map(PrivilegeDTO::toPrivilege).toList())
                .establishment(establishment)
                .build();
    }

    @SearchRole
    @CheckCompanyCoherence
    @Override
    public Role updateRole(@CompanyId String companyId, @RoleId String roleId, @Valid RoleUpdatedInformation updates) {
        Optional<Establishment> establishment = establishmentRepository.findById(updates.establishmentId());

        Role role = SearchingAspect.ROLE_FOUND.get();

        if (updates.name() != null && !updates.name().isBlank()) {
            role.setName(updates.name());
        }

        if (updates.description() != null) {
            role.setDescription(updates.description());
        }

        if (updates.privileges() != null) {
            role.setPrivileges(updates.privileges().stream().map(PrivilegeDTO::toPrivilege).toList());
        } else {
            role.setPrivileges(Lists.newArrayList());
        }

        role.setEstablishment(establishment.orElse(null));

        return roleRepository.save(role);
    }

    @SearchRole
    @CheckCompanyCoherence
    @Override
    public void deleteRole(@CompanyId String companyId, @RoleId String roleId) {
        roleRepository.delete(SearchingAspect.ROLE_FOUND.get());
    }
}

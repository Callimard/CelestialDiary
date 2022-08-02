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
import org.bandrsoftwares.celestialdiary.model.dto.employee.EstablishmentRoleDTO;
import org.bandrsoftwares.celestialdiary.model.mongodb.company.Company;
import org.bandrsoftwares.celestialdiary.model.mongodb.employee.EstablishmentRole;
import org.bandrsoftwares.celestialdiary.security.privilege.Privilege;
import org.bandrsoftwares.celestialdiary.model.mongodb.employee.Role;
import org.bandrsoftwares.celestialdiary.model.mongodb.employee.RoleRepository;
import org.bandrsoftwares.celestialdiary.model.mongodb.establishment.Establishment;
import org.bandrsoftwares.celestialdiary.model.mongodb.establishment.EstablishmentRepository;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;
import java.util.function.Function;

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
        return Role.builder()
                .name(info.name())
                .description(info.description())
                .companyPrivileges(info.companyPrivilegeIdentifiers() != null ?
                                           info.companyPrivilegeIdentifiers().stream().map(Privilege::extractPrivilegeFromIdentifier).toList() :
                                           Lists.newArrayList())
                .establishmentRoles(info.establishmentRoles() != null ?
                                            info.establishmentRoles().stream().map(extractEstablishmentRoleFromDTO()).toList() : Lists.newArrayList())
                .company(company)
                .build();
    }

    private Function<EstablishmentRoleDTO, EstablishmentRole> extractEstablishmentRoleFromDTO() {
        return eRole -> {
            Optional<Establishment> opEstablishment = establishmentRepository.findById(eRole.establishmentId());
            return opEstablishment.map(establishment -> createEstablishmentRoleFrom(establishment, eRole)).orElse(null);
        };
    }

    private EstablishmentRole createEstablishmentRoleFrom(Establishment establishment, EstablishmentRoleDTO eRole) {
        return EstablishmentRole.builder()
                .establishment(establishment)
                .establishmentPrivileges(eRole.establishmentPrivileges().stream()
                                                 .map(privilegeDTO -> Privilege.extractPrivilegeFromIdentifier(privilegeDTO.identifierName()))
                                                 .toList())
                .build();
    }

    @SearchRole
    @CheckCompanyCoherence
    @Override
    public Role updateRole(@CompanyId String companyId, @RoleId String roleId, @Valid RoleUpdatedInformation updates) {

        Role role = SearchingAspect.ROLE_FOUND.get();

        if (updates.name() != null && !updates.name().isBlank()) {
            role.setName(updates.name());
        }

        if (updates.description() != null) {
            role.setDescription(updates.description());
        }

        if (updates.companyPrivilegeIdentifiers() != null) {
            role.setCompanyPrivileges(updates.companyPrivilegeIdentifiers().stream().map(Privilege::extractPrivilegeFromIdentifier).toList());
        } else {
            role.setCompanyPrivileges(Lists.newArrayList());
        }

        if (updates.establishmentRoles() != null) {
            role.setEstablishmentRoles(updates.establishmentRoles().stream().map(extractEstablishmentRoleFromDTO()).toList());
        } else {
            role.setEstablishmentRoles(Lists.newArrayList());
        }

        return roleRepository.save(role);
    }

    @SearchRole
    @CheckCompanyCoherence
    @Override
    public void deleteRole(@CompanyId String companyId, @RoleId String roleId) {
        roleRepository.delete(SearchingAspect.ROLE_FOUND.get());
    }
}

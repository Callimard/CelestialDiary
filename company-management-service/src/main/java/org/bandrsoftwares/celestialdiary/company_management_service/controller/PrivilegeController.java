package org.bandrsoftwares.celestialdiary.company_management_service.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.bandrsoftwares.celestialdiary.security.privilege.company.CompanyManagementScope;
import org.bandrsoftwares.celestialdiary.security.privilege.dto.ScopeDTO;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static org.bandrsoftwares.celestialdiary.api.v1.ApiPrivilegeV1.COMPANY_PRIVILEGE;
import static org.bandrsoftwares.celestialdiary.api.v1.ApiPrivilegeV1.PRIVILEGES_URL;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping(PRIVILEGES_URL)
public class PrivilegeController {

    private static final CompanyManagementScope COMPANY_MANAGEMENT_SCOPE = new CompanyManagementScope();

    @GetMapping(COMPANY_PRIVILEGE)
    public ScopeDTO getCompanyManagementScope() {
        return new ScopeDTO(COMPANY_MANAGEMENT_SCOPE);
    }
}

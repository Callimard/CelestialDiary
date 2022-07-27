package org.bandrsoftwares.celestialdiary.company_management_service.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.bandrsoftwares.celestialdiary.model.dto.employee.PrivilegeDTO;
import org.bandrsoftwares.celestialdiary.model.mongodb.employee.Privilege;
import org.bandrsoftwares.celestialdiary.security.privilege.company.CompanyManagementPrivilege;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;

import static org.bandrsoftwares.celestialdiary.api.v1.ApiPrivilegeV1.COMPANY_PRIVILEGE;
import static org.bandrsoftwares.celestialdiary.api.v1.ApiPrivilegeV1.PRIVILEGES_URL;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping(PRIVILEGES_URL)
public class PrivilegeController {

    @GetMapping(COMPANY_PRIVILEGE)
    public List<PrivilegeDTO> getAllCompanyPrivilege() {
        return Arrays.stream(CompanyManagementPrivilege.values()).map(privilegeEnum -> new PrivilegeDTO(Privilege.of(privilegeEnum))).toList();
    }
}

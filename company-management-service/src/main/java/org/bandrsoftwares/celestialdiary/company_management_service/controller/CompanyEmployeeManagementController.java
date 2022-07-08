package org.bandrsoftwares.celestialdiary.company_management_service.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.bandrsoftwares.celestialdiary.company_management_service.service.CompanyEmployeeManagementService;
import org.bandrsoftwares.celestialdiary.model.mongodb.employee.Employee;
import org.bandrsoftwares.celestialdiary.model.mongodb.employee.WrappedEmployeeDTO;
import org.bandrsoftwares.celestialdiary.security.privilege.company.*;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.bandrsoftwares.celestialdiary.api.v1.ApiCompanyV1.*;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping(V1_COMPANY_MANAGEMENT)
public class CompanyEmployeeManagementController {

    // Variables.

    private final CompanyEmployeeManagementService companyEmployeeManagementService;

    // Entry points.

    @ReadCompanyEmployeePrivilege
    @GetMapping(EMPLOYEES)
    public List<WrappedEmployeeDTO> getEmployee(@PathVariable(name = "idCompany") String idCompany,
                                                @RequestParam(name = "all", required = false, defaultValue = "false") Boolean all,
                                                @RequestParam(name = "active", required = false) Boolean active,
                                                @RequestParam(name = "technician", required = false) Boolean technician) {

        if (noEmployeeFilter(all, active, technician) || allFilter(all)) {
            return companyEmployeeManagementService.allRegisteredEmployees(idCompany).stream().map(WrappedEmployeeDTO::new).toList();
        } else if (activeFilter(active) && noTechnicianFilter(technician)) {
            return companyEmployeeManagementService.allRegisteredEmployees(idCompany, active).stream().map(WrappedEmployeeDTO::new)
                    .toList();
        } else if (noActiveFilter(active) && technicianFilter(technician)) {
            return companyEmployeeManagementService.allRegisteredEmployees(idCompany, technician, null).stream().map(WrappedEmployeeDTO::new)
                    .toList();
        } else {
            return companyEmployeeManagementService.allRegisteredEmployees(idCompany, active, technician).stream().map(WrappedEmployeeDTO::new)
                    .toList();
        }
    }

    private boolean allFilter(Boolean all) {
        return all != null && all;
    }

    private boolean noEmployeeFilter(Boolean all, Boolean active, Boolean technician) {
        return (all == null || !all) && active == null && technician == null;
    }

    private boolean technicianFilter(@RequestParam(name = "technician", required = false) Boolean technician) {
        return technician != null;
    }

    private boolean noTechnicianFilter(@RequestParam(name = "technician", required = false) Boolean technician) {
        return technician == null;
    }

    private boolean activeFilter(@RequestParam(name = "active", required = false) Boolean active) {
        return active != null;
    }

    private boolean noActiveFilter(@RequestParam(name = "active", required = false) Boolean active) {
        return active == null;
    }

    @CreateCompanyEmployeePrivilege
    @PostMapping(EMPLOYEES)
    public WrappedEmployeeDTO createEmployee(@PathVariable(name = "idCompany") String idCompany,
                                             @RequestBody CompanyEmployeeManagementService.EmployeeCreationInformation employeeCreationInformation) {
        Employee employee = companyEmployeeManagementService.createEmployee(idCompany, employeeCreationInformation);
        return new WrappedEmployeeDTO(employee);
    }

    @UpdateCompanyEmployeePrivilege
    @PutMapping(SPECIFIC_EMPLOYEE)
    public WrappedEmployeeDTO updateEmployeeInformation(@PathVariable(name = "idCompany") String idCompany, @PathVariable String idEmployee,
                                                        @RequestBody CompanyEmployeeManagementService.EmployeeUpdatedInformation employeeUpdatedInformation) {
        Employee employee = companyEmployeeManagementService.updateEmployeeInformation(idCompany, idEmployee, employeeUpdatedInformation);
        return new WrappedEmployeeDTO(employee);
    }

    @UpdateCompanyEmployeeRolePrivilege
    @PutMapping(SPECIFIC_EMPLOYEE_ROLES)
    public WrappedEmployeeDTO updateEmployeeRoles(@PathVariable(name = "idCompany") String idCompany, @PathVariable String idEmployee,
                                                  @RequestBody CompanyEmployeeManagementService.EmployeeUpdatedRoles employeeUpdatedRoles) {
        Employee employee = companyEmployeeManagementService.updateEmployeeRoles(idCompany, idEmployee, employeeUpdatedRoles);
        return new WrappedEmployeeDTO(employee);
    }

    @AssignCompanyEmployeePrivilege
    @PostMapping(SPECIFIC_EMPLOYEE_ESTABLISHMENT_ASSIGNATION)
    public boolean assignEmployeeToEstablishment(@PathVariable(name = "idCompany") String idCompany, @PathVariable String idEmployee,
                                                 @PathVariable String idEstablishment) {
        return companyEmployeeManagementService.assignEmployeeToEstablishment(idCompany, idEmployee, idEstablishment);
    }

    @AssignCompanyEmployeePrivilege
    @DeleteMapping(SPECIFIC_EMPLOYEE_ESTABLISHMENT_ASSIGNATION)
    public boolean deAssignEmployeeToEstablishment(@PathVariable(name = "idCompany") String idCompany, @PathVariable String idEmployee,
                                                   @PathVariable String idEstablishment) {
        return companyEmployeeManagementService.deAssignEmployeeToEstablishment(idCompany, idEmployee, idEstablishment);
    }

    @ActivateCompanyEmployeePrivilege
    @PostMapping(SPECIFIC_EMPLOYEE_ACTIVATION)
    public boolean activateEmployee(@PathVariable(name = "idCompany") String idCompany, @PathVariable String idEmployee) {
        return companyEmployeeManagementService.activateEmployee(idCompany, idEmployee);
    }

    @ActivateCompanyEmployeePrivilege
    @DeleteMapping(SPECIFIC_EMPLOYEE_ACTIVATION)
    public boolean deactivateEmployee(@PathVariable(name = "idCompany") String idCompany, @PathVariable String idEmployee) {
        return companyEmployeeManagementService.deactivateEmployee(idCompany, idEmployee);
    }

}

package org.bandrsoftwares.celestialdiary.company_management_service.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.bandrsoftwares.celestialdiary.company_management_service.service.EmployeeManagementService;
import org.bandrsoftwares.celestialdiary.model.dto.employee.EmployeeDTO;
import org.bandrsoftwares.celestialdiary.model.dto.employee.WrappedEmployeeDTO;
import org.bandrsoftwares.celestialdiary.model.mongodb.employee.Employee;
import org.bandrsoftwares.celestialdiary.security.privilege.company.employee.*;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.bandrsoftwares.celestialdiary.api.v1.ApiCompanyV1.*;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping(EMPLOYEES_URL)
public class EmployeeManagementController {

    // Variables.

    private final EmployeeManagementService employeeManagementService;

    // Entry points.

    @ReadEmployeePrivilege
    @GetMapping
    public List<WrappedEmployeeDTO> getEmployees(@PathVariable(name = "idCompany") String idCompany,
                                                 @RequestParam(name = "filter", required = false) String filter) {

        if (filter == null) {
            return employeeManagementService.allRegisteredEmployees(idCompany).stream().map(WrappedEmployeeDTO::new).toList();
        } else {
            return employeeManagementService.searchEmployee(idCompany, filter).stream().map(WrappedEmployeeDTO::new).toList();
        }
    }

    @CreateEmployeePrivilege
    @PostMapping
    public WrappedEmployeeDTO createEmployee(@PathVariable(name = "idCompany") String idCompany,
                                             @RequestBody EmployeeManagementService.EmployeeCreationInformation employeeCreationInformation) {
        Employee employee = employeeManagementService.createEmployee(idCompany, employeeCreationInformation);
        return new WrappedEmployeeDTO(employee);
    }

    @ReadEmployeePrivilege
    @GetMapping(SPECIFIC_EMPLOYEE)
    public EmployeeDTO getSpecificEmployee(@PathVariable(name = "idCompany") String idCompany,
                                           @PathVariable(name = "idEmployee") String idEmployee) {
        return new EmployeeDTO(employeeManagementService.getSpecificEmployee(idCompany, idEmployee));
    }

    @UpdateEmployeePrivilege
    @PutMapping(SPECIFIC_EMPLOYEE)
    public WrappedEmployeeDTO updateEmployeeInformation(@PathVariable(name = "idCompany") String idCompany,
                                                        @PathVariable(name = "idEmployee") String idEmployee,
                                                        @RequestBody EmployeeManagementService.EmployeeUpdatedInformation employeeUpdatedInformation) {
        Employee employee = employeeManagementService.updateEmployeeInformation(idCompany, idEmployee, employeeUpdatedInformation);
        return new WrappedEmployeeDTO(employee);
    }

    @UpdateEmployeeRolePrivilege
    @PutMapping(SPECIFIC_EMPLOYEE_ROLES)
    public WrappedEmployeeDTO updateEmployeeRoles(@PathVariable(name = "idCompany") String idCompany,
                                                  @PathVariable(name = "idEmployee") String idEmployee,
                                                  @RequestBody EmployeeManagementService.EmployeeUpdatedRoles employeeUpdatedRoles) {
        Employee employee = employeeManagementService.updateEmployeeRoles(idCompany, idEmployee, employeeUpdatedRoles);
        return new WrappedEmployeeDTO(employee);
    }

    @AssignEmployeePrivilege
    @PostMapping(SPECIFIC_EMPLOYEE_ESTABLISHMENT_ASSIGNATION)
    public boolean assignEmployeeToEstablishment(@PathVariable(name = "idCompany") String idCompany,
                                                 @PathVariable(name = "idEmployee") String idEmployee,
                                                 @PathVariable(name = "idEstablishment") String idEstablishment) {
        return employeeManagementService.assignEmployeeToEstablishment(idCompany, idEmployee, idEstablishment);
    }

    @AssignEmployeePrivilege
    @DeleteMapping(SPECIFIC_EMPLOYEE_ESTABLISHMENT_ASSIGNATION)
    public boolean deAssignEmployeeToEstablishment(@PathVariable(name = "idCompany") String idCompany,
                                                   @PathVariable(name = "idEmployee") String idEmployee,
                                                   @PathVariable(name = "idEstablishment") String idEstablishment) {
        return employeeManagementService.deAssignEmployeeToEstablishment(idCompany, idEmployee, idEstablishment);
    }

    @ActivateEmployeePrivilege
    @PutMapping(SPECIFIC_EMPLOYEE_ACTIVATION)
    public boolean activateEmployee(@PathVariable(name = "idCompany") String idCompany, @PathVariable(name = "idEmployee") String idEmployee) {
        return employeeManagementService.activateEmployee(idCompany, idEmployee);
    }

    @ActivateEmployeePrivilege
    @DeleteMapping(SPECIFIC_EMPLOYEE_ACTIVATION)
    public boolean deactivateEmployee(@PathVariable(name = "idCompany") String idCompany, @PathVariable(name = "idEmployee") String idEmployee) {
        return employeeManagementService.deactivateEmployee(idCompany, idEmployee);
    }

}

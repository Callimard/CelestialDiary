package org.bandrsoftwares.celestialdiary.company_management_service.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.bandrsoftwares.celestialdiary.company_management_service.service.EmployeeManagementService;
import org.bandrsoftwares.celestialdiary.model.dto.person.employee.EmployeeDTO;
import org.bandrsoftwares.celestialdiary.model.dto.person.employee.WrappedEmployeeDTO;
import org.bandrsoftwares.celestialdiary.model.dto.person.employee.WrappedEmployeeWorkingHoursDTO;
import org.bandrsoftwares.celestialdiary.model.mongodb.person.employee.Employee;
import org.bandrsoftwares.celestialdiary.model.mongodb.person.employee.EmployeeWorkingHours;
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
    public EmployeeDTO updateEmployeeInformation(@PathVariable(name = "idCompany") String idCompany,
                                                 @PathVariable(name = "idEmployee") String idEmployee,
                                                 @RequestBody EmployeeManagementService.EmployeeUpdatedInformation employeeUpdatedInformation) {
        Employee employee = employeeManagementService.updateEmployeeInformation(idCompany, idEmployee, employeeUpdatedInformation);
        return new EmployeeDTO(employee);
    }

    @ReadEmployeePrivilege
    @GetMapping(SPECIFIC_EMPLOYEE_WORKING_HOURS)
    public WrappedEmployeeWorkingHoursDTO getEmployeeWorkingHours(@PathVariable(name = "idCompany") String idCompany,
                                                                  @PathVariable(name = "idEmployee") String idEmployee,
                                                                  @PathVariable(name = "idEstablishment") String idEstablishment,
                                                                  @RequestParam(name = "year") int year,
                                                                  @RequestParam(name = "weekNumber") int weekNumber) {
        EmployeeWorkingHours employeeWorkingHours = employeeManagementService.getEmployeeWorkingHours(idCompany, idEmployee, idEstablishment, year,
                                                                                                      weekNumber);
        if (employeeWorkingHours != null) {
            return new WrappedEmployeeWorkingHoursDTO(employeeWorkingHours);
        } else {
            return null;
        }
    }

    @UpdateEmployeePrivilege
    @PutMapping(SPECIFIC_EMPLOYEE_WORKING_HOURS)
    public WrappedEmployeeWorkingHoursDTO updateEmployeeWorkingHours(@PathVariable(name = "idCompany") String idCompany,
                                                                     @PathVariable(name = "idEmployee") String idEmployee,
                                                                     @PathVariable(name = "idEstablishment") String idEstablishment,
                                                                     @RequestBody EmployeeManagementService.WorkingHoursInformation info) {
        return new WrappedEmployeeWorkingHoursDTO(employeeManagementService.updateEmployeeWorkingHours(idCompany, idEmployee, idEstablishment, info));
    }

    @UpdateEmployeeRolePrivilege
    @PutMapping(SPECIFIC_EMPLOYEE_ROLES)
    public EmployeeDTO updateEmployeeRoles(@PathVariable(name = "idCompany") String idCompany,
                                           @PathVariable(name = "idEmployee") String idEmployee,
                                           @RequestBody EmployeeManagementService.EmployeeUpdatedRoles employeeUpdatedRoles) {
        Employee employee = employeeManagementService.updateEmployeeRoles(idCompany, idEmployee, employeeUpdatedRoles);
        return new EmployeeDTO(employee);
    }

    @AssignEmployeePrivilege
    @PutMapping(SPECIFIC_EMPLOYEE_ESTABLISHMENT_ASSIGNATION)
    public EmployeeDTO updateEmployeeEstablishmentAssignation(@PathVariable(name = "idCompany") String idCompany,
                                                              @PathVariable(name = "idEmployee") String idEmployee,
                                                              @RequestBody EmployeeManagementService.EmployeeEstablishmentInformation updates) {
        return new EmployeeDTO(employeeManagementService.updateEmployeeEstablishment(idCompany, idEmployee, updates));
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

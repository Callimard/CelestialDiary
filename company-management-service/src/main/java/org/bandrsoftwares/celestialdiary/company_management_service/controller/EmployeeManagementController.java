package org.bandrsoftwares.celestialdiary.company_management_service.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.bandrsoftwares.celestialdiary.company_management_service.service.EmployeeManagementService;
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
                                                 @RequestParam(name = "all", required = false, defaultValue = "false") Boolean all,
                                                 @RequestParam(name = "active", required = false) Boolean active,
                                                 @RequestParam(name = "technician", required = false) Boolean technician) {

        if (noEmployeeFilter(all, active, technician) || allFilter(all)) {
            return employeeManagementService.allRegisteredEmployees(idCompany).stream().map(WrappedEmployeeDTO::new).toList();
        } else if (activeFilter(active) && noTechnicianFilter(technician)) {
            return employeeManagementService.allRegisteredEmployees(idCompany, active).stream().map(WrappedEmployeeDTO::new)
                    .toList();
        } else if (noActiveFilter(active) && technicianFilter(technician)) {
            return employeeManagementService.allRegisteredEmployees(idCompany, technician, null).stream().map(WrappedEmployeeDTO::new)
                    .toList();
        } else {
            return employeeManagementService.allRegisteredEmployees(idCompany, active, technician).stream().map(WrappedEmployeeDTO::new)
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

    @CreateEmployeePrivilege
    @PostMapping
    public WrappedEmployeeDTO createEmployee(@PathVariable(name = "idCompany") String idCompany,
                                             @RequestBody EmployeeManagementService.EmployeeCreationInformation employeeCreationInformation) {
        Employee employee = employeeManagementService.createEmployee(idCompany, employeeCreationInformation);
        return new WrappedEmployeeDTO(employee);
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

package org.bandrsoftwares.celestialdiary.company_management_service.service;

import lombok.NonNull;
import org.bandrsoftwares.celestialdiary.aop.company.CompanyCoherenceException;
import org.bandrsoftwares.celestialdiary.aop.company.CompanyId;
import org.bandrsoftwares.celestialdiary.aop.company.CompanyNotFoundException;
import org.bandrsoftwares.celestialdiary.aop.employee.EmployeeId;
import org.bandrsoftwares.celestialdiary.aop.employee.EmployeeNotFoundException;
import org.bandrsoftwares.celestialdiary.aop.establishment.EstablishmentId;
import org.bandrsoftwares.celestialdiary.model.mongodb.employee.Employee;
import org.bandrsoftwares.celestialdiary.model.mongodb.employee.PersonGender;
import org.bandrsoftwares.celestialdiary.validation.ValidEmail;
import org.bandrsoftwares.celestialdiary.validation.ValidPassword;
import org.bandrsoftwares.celestialdiary.validation.ValidPhone;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.List;

public interface EmployeeManagementService {

    /**
     * @param companyId the company id
     *
     * @return the list of all employees in the company
     */
    List<Employee> allRegisteredEmployees(@CompanyId String companyId);

    /**
     * @param companyId the company id
     * @param filter    the filter (either first name, either last name or either email)
     *
     * @return the list of employee corresponding to the filter
     */
    List<Employee> searchEmployee(@CompanyId String companyId, String filter);

    /**
     * @param companyId  the company id
     * @param employeeId the employee id
     *
     * @return the employee if it has been found and is coherent with the specified company id.
     *
     * @throws EmployeeNotFoundException if the employee is not found
     * @throws CompanyCoherenceException if the company id is not coherent with the employee
     */
    Employee getSpecificEmployee(@CompanyId String companyId, @EmployeeId String employeeId);

    /**
     * Create and save the new employee. The specified email must be unique in the company.
     *
     * @param companyId                   the company where the employee is created
     * @param employeeCreationInformation the employee creation information
     *
     * @return the created employee entity.
     *
     * @throws CompanyNotFoundException if the company is not found.
     */
    Employee createEmployee(@CompanyId String companyId, @Valid EmployeeCreationInformation employeeCreationInformation);

    record EmployeeCreationInformation(@NotNull @ValidEmail String email, @NotNull @ValidPassword String password,
                                       @NotNull @NotBlank String firstName, @NotNull @NotBlank String lastName, String comment,
                                       PersonGender gender, @ValidPhone String phone, @NonNull Boolean isTechnician,
                                       @NonNull List<String> tags, @NonNull List<String> roles) {
    }

    /**
     * Update the information of the Employee. Updated information are only not null specified information. Null specified information are ignored and
     * not changed.
     *
     * @param companyId                  the id of the company
     * @param employeeId                 the id of the employee to update
     * @param employeeUpdatedInformation the information to update
     *
     * @return the updated Employee entity
     *
     * @throws CompanyCoherenceException if the employee company is not the specified company
     */
    Employee updateEmployeeInformation(@CompanyId String companyId, @EmployeeId String employeeId,
                                       @Valid EmployeeUpdatedInformation employeeUpdatedInformation);

    record EmployeeUpdatedInformation(@ValidPassword String password, String firstName, String lastName, String comment,
                                      PersonGender gender, @ValidPhone String phone, Boolean isTechnician,
                                      List<String> tags) {

    }

    /**
     * Update Employee roles. Remove and add new role of the Employee
     *
     * @param companyId            the id of the company
     * @param employeeId           the id of the employee to update
     * @param employeeUpdatedRoles role to remove and to add
     *
     * @return the updated Employee entity
     *
     * @throws CompanyCoherenceException if the employee company is not the specified company
     */
    Employee updateEmployeeRoles(@CompanyId String companyId, @EmployeeId String employeeId, @Valid EmployeeUpdatedRoles employeeUpdatedRoles);

    record EmployeeUpdatedRoles(@NotNull List<String> rolesToRemove, @NotNull List<String> rolesToAdd) {

        boolean hasRolesToRemove() {
            return rolesToRemove != null && !rolesToRemove.isEmpty();
        }

        boolean hasRolesToAdd() {
            return rolesToAdd != null && !rolesToAdd.isEmpty();
        }

        boolean isEmpty() {
            return !hasRolesToRemove() && !hasRolesToAdd();
        }
    }

    /**
     * Assigns the Employee to the Establishment. The Establishment must be an Establishment of the Employee Company.
     *
     * @param companyId       the id of the company
     * @param employeeId      the id of the employee to assign
     * @param establishmentId the id of the establishment
     *
     * @return true if changes has been done, else false.
     *
     * @throws CompanyCoherenceException if the employee company is not the specified company
     */
    boolean assignEmployeeToEstablishment(@CompanyId String companyId, @EmployeeId String employeeId, @EstablishmentId String establishmentId);

    /**
     * De assigns the Employee to the Establishment. The Establishment must be an Establishment of the Employee Company.
     *
     * @param companyId       the id of the company
     * @param employeeId      the id of the employee to de assign
     * @param establishmentId the id of the establishment
     *
     * @return true if changes has been done, else false.
     *
     * @throws CompanyCoherenceException if the employee company is not the specified company
     */
    boolean deAssignEmployeeToEstablishment(@CompanyId String companyId, @EmployeeId String employeeId, @EstablishmentId String establishmentId);

    /**
     * Activate the Employee if it is not already the case.
     *
     * @param companyId  the id of the company
     * @param employeeId the id of the employee to activate
     *
     * @return true if changes has been done, else false.
     *
     * @throws CompanyCoherenceException if the employee company is not the specified company
     */
    boolean activateEmployee(@CompanyId String companyId, @EmployeeId String employeeId);

    /**
     * Deactivate the Employee if it is not already the case.
     *
     * @param companyId  the id of the company
     * @param employeeId the id of the employee to deactivate
     *
     * @return true if changes has been done, else false.
     */
    boolean deactivateEmployee(@CompanyId String companyId, @EmployeeId String employeeId);
}

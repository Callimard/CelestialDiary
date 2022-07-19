package org.bandrsoftwares.celestialdiary.company_management_service.service;

import com.google.common.collect.Sets;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.bandrsoftwares.celestialdiary.aop.SearchingAspect;
import org.bandrsoftwares.celestialdiary.aop.company.CheckCompanyCoherence;
import org.bandrsoftwares.celestialdiary.aop.company.CompanyId;
import org.bandrsoftwares.celestialdiary.aop.company.SearchCompany;
import org.bandrsoftwares.celestialdiary.aop.employee.EmployeeId;
import org.bandrsoftwares.celestialdiary.aop.employee.SearchEmployee;
import org.bandrsoftwares.celestialdiary.aop.establishment.EstablishmentId;
import org.bandrsoftwares.celestialdiary.aop.establishment.SearchEstablishment;
import org.bandrsoftwares.celestialdiary.model.mongodb.company.Company;
import org.bandrsoftwares.celestialdiary.model.mongodb.employee.*;
import org.bandrsoftwares.celestialdiary.model.mongodb.establishment.Establishment;
import org.bandrsoftwares.celestialdiary.model.mongodb.establishment.EstablishmentRepository;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;
import java.time.Instant;
import java.util.List;
import java.util.Set;

@Slf4j
@RequiredArgsConstructor
@Validated
@Service
public class EmployeeManagementServiceImpl implements EmployeeManagementService {

    // Variables.

    private final EmployeeRepository employeeRepository;
    private final RoleRepository roleRepository;
    private final EstablishmentRepository establishmentRepository;

    // Methods.

    @SearchCompany
    @Override
    public List<Employee> allRegisteredEmployees(@CompanyId String companyId) {
        return employeeRepository.findByCompany(SearchingAspect.COMPANY_FOUND.get());
    }

    @SearchCompany
    @Override
    public List<Employee> searchEmployee(@CompanyId String companyId, @NonNull String filter) {
        String regexFilter = ".*" + filter + ".*";
        return employeeRepository.findByCompanyAndFirstNameRegexOrLastNameRegexOrEmailRegex(SearchingAspect.COMPANY_FOUND.get(), regexFilter,
                                                                                            regexFilter, regexFilter);
    }

    @SearchEmployee
    @CheckCompanyCoherence
    @Override
    public Employee getSpecificEmployee(@CompanyId String companyId, @EmployeeId String employeeId) {
        return SearchingAspect.EMPLOYEE_FOUND.get();
    }

    @SearchCompany
    @Override
    public Employee createEmployee(@CompanyId String companyId, @Valid EmployeeCreationInformation employeeCreationInformation) {
        Company company = SearchingAspect.COMPANY_FOUND.get();

        List<Role> roles = roleRepository.findByCompanyAndIdIn(company, employeeCreationInformation.roles());
        Employee employee = createEmployeeFrom(company, employeeCreationInformation, roles);
        return employeeRepository.insert(employee);
    }

    private Employee createEmployeeFrom(Company company, @Valid EmployeeCreationInformation employeeCreationInformation, List<Role> roles) {
        return Employee.builder()
                .email(employeeCreationInformation.email())
                .password(employeeCreationInformation.password())
                .firstName(employeeCreationInformation.firstName())
                .lastName(employeeCreationInformation.lastName())
                .comment(employeeCreationInformation.comment())
                .gender(employeeCreationInformation.gender() != null ? employeeCreationInformation.gender() : PersonGender.NO_GENDER)
                .phone(employeeCreationInformation.phone())
                .isTechnician(employeeCreationInformation.isTechnician())
                .activated(true)
                .tags(employeeCreationInformation.tags())
                .roles(roles)
                .company(company)
                .creationDate(Instant.now())
                .build();
    }

    @SearchEmployee
    @CheckCompanyCoherence
    @Override
    public Employee updateEmployeeInformation(@CompanyId String companyId, @EmployeeId String employeeId, @Valid EmployeeUpdatedInformation update) {
        Employee employee = SearchingAspect.EMPLOYEE_FOUND.get();

        if (update.password() != null && !update.password().isBlank()) {
            employee.setPassword(update.password());
        }

        if (update.firstName() != null) {
            employee.setFirstName(update.firstName());
        }

        if (update.lastName() != null) {
            employee.setLastName(update.lastName());
        }

        if (update.comment() != null) {
            employee.setComment(update.comment());
        }

        if (update.gender() != null) {
            employee.setGender(update.gender());
        }

        if (update.phone() != null) {
            employee.setPhone(update.phone());
        }

        if (update.isTechnician() != null) {
            employee.setIsTechnician(update.isTechnician());
        }

        if (update.tags() != null) {
            employee.setTags(update.tags());
        }

        return employeeRepository.save(employee);
    }

    @SearchCompany
    @SearchEmployee
    @CheckCompanyCoherence
    @Override
    public Employee updateEmployeeRoles(@CompanyId String companyId, @EmployeeId String employeeId,
                                        @Valid EmployeeUpdatedRoles employeeUpdatedRoles) {
        Company company = SearchingAspect.COMPANY_FOUND.get();
        Employee employee = SearchingAspect.EMPLOYEE_FOUND.get();

        Set<String> rolesToRemove = Sets.newHashSet(employeeUpdatedRoles.rolesToRemove());
        Set<String> rolesToAdd = Sets.newHashSet(employeeUpdatedRoles.rolesToAdd());

        employee.getRoles().removeIf(r -> rolesToRemove.contains(r.getId()));

        List<Role> newRoles = roleRepository.findByCompanyAndIdIn(company, rolesToAdd);
        employee.getRoles().addAll(newRoles);

        return employeeRepository.save(employee);
    }

    @SearchEmployee
    @SearchEstablishment
    @CheckCompanyCoherence
    @Override
    public boolean assignEmployeeToEstablishment(@CompanyId String companyId, @EmployeeId String employeeId,
                                                 @EstablishmentId String establishmentId) {
        Employee employee = SearchingAspect.EMPLOYEE_FOUND.get();
        Establishment establishment = SearchingAspect.ESTABLISHMENT_FOUND.get();

        if (!establishment.hasAsAssignedEmployee(employee)) {
            establishment.getAssignedEmployees().add(employee);
            establishmentRepository.save(establishment);

            if (!employee.isAssignedTo(establishment)) {
                employee.getAssignedEstablishments().add(establishment);
                employeeRepository.save(employee);
            }

            return true;
        } else {
            // Just in case employee is not up-to-date
            if (!employee.isAssignedTo(establishment)) {
                employee.getAssignedEstablishments().add(establishment);
                employeeRepository.save(employee);
                return true;
            }
            return false;
        }
    }

    @SearchEmployee
    @SearchEstablishment
    @CheckCompanyCoherence
    @Override
    public boolean deAssignEmployeeToEstablishment(@CompanyId String companyId, @EmployeeId String employeeId,
                                                   @EstablishmentId String establishmentId) {
        Employee employee = SearchingAspect.EMPLOYEE_FOUND.get();
        Establishment establishment = SearchingAspect.ESTABLISHMENT_FOUND.get();

        if (establishment.hasAsAssignedEmployee(employee)) {
            establishment.getAssignedEmployees().removeIf(emp -> emp.getId().equals(employeeId));
            employee.getAssignedEstablishments().removeIf(est -> est.getId().equals(establishment.getId()));

            employeeRepository.save(employee);
            establishmentRepository.save(establishment);
            return true;
        } else {
            // Just in case employee is not up-to-date
            if (employee.isAssignedTo(establishment)) {
                employee.getAssignedEstablishments().removeIf(est -> est.getId().equals(establishment.getId()));
                employeeRepository.save(employee);
                return true;
            }
            return false;
        }
    }

    @SearchEmployee
    @CheckCompanyCoherence
    @Override
    public boolean activateEmployee(@CompanyId String companyId, @EmployeeId String employeeId) {
        Employee employee = SearchingAspect.EMPLOYEE_FOUND.get();
        boolean activated = employee.getActivated();
        if (!activated) {
            employee.setActivated(true);
            employeeRepository.save(employee);
            return true;
        }
        return false;
    }

    @SearchEmployee
    @CheckCompanyCoherence
    @Override
    public boolean deactivateEmployee(@CompanyId String companyId, @EmployeeId String employeeId) {
        Employee employee = SearchingAspect.EMPLOYEE_FOUND.get();
        boolean activated = employee.getActivated();
        if (activated) {
            employee.setActivated(false);
            employeeRepository.save(employee);
            return true;
        }
        return false;
    }
}

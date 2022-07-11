package org.bandrsoftwares.celestialdiary.company_management_service.service;

import com.google.common.collect.Sets;
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
import org.bandrsoftwares.celestialdiary.model.mongodb.establishment.EstablishmentSummary;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;
import javax.validation.constraints.Null;
import java.time.Instant;
import java.util.List;
import java.util.Set;

@Slf4j
@RequiredArgsConstructor
@Validated
@Service
public class CompanyEmployeeManagementServiceImpl implements CompanyEmployeeManagementService {

    // Variables.

    private final EmployeeRepository employeeRepository;
    private final RoleRepository roleRepository;
    private final EstablishmentRepository establishmentRepository;

    // Methods.

    @SearchCompany
    @Override
    public List<Employee> allRegisteredEmployees(@CompanyId String companyId) {
        return employeeRepository.findByCompanySummaryCompany(SearchingAspect.COMPANY_FOUND.get());
    }

    @SearchCompany
    @Override
    public List<Employee> allRegisteredEmployees(@CompanyId String companyId, boolean active) {
        return employeeRepository.findByCompanySummaryCompanyAndActive(SearchingAspect.COMPANY_FOUND.get(), active);
    }

    @SearchCompany
    @Override
    public List<Employee> allRegisteredEmployees(@CompanyId String companyId, boolean technician, @Null String useless) {
        return employeeRepository.findByCompanySummaryCompanyAndIsTechnician(SearchingAspect.COMPANY_FOUND.get(), technician);
    }

    @SearchCompany
    @Override
    public List<Employee> allRegisteredEmployees(@CompanyId String companyId, boolean active, boolean technician) {
        return employeeRepository.findByCompanySummaryCompanyAndActiveAndIsTechnician(SearchingAspect.COMPANY_FOUND.get(), active, technician);
    }

    @SearchCompany
    @Override
    public Employee createEmployee(@CompanyId String companyId, @Valid EmployeeCreationInformation employeeCreationInformation) {
        List<Role> roles = roleRepository.findByIdIn(employeeCreationInformation.roles());
        Employee employee = createEmployeeFrom(SearchingAspect.COMPANY_FOUND.get(), employeeCreationInformation, roles);
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
                .active(true)
                .tags(Sets.newHashSet(employeeCreationInformation.tags()))
                .roles(roles)
                .companySummary(company.summary())
                .creationDate(Instant.now())
                .build();
    }

    @SearchEmployee
    @CheckCompanyCoherence
    @Override
    public Employee updateEmployeeInformation(@CompanyId String companyId, @EmployeeId String employeeId, @Valid EmployeeUpdatedInformation update) {
        Employee employee = SearchingAspect.EMPLOYEE_FOUND.get();

        if (update.password() != null) {
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
            if (employee.getTags() == null) {
                employee.setTags(Sets.newHashSet(update.tags()));
            } else {
                employee.getTags().addAll(update.tags());
            }
        }

        return employeeRepository.save(employee);
    }

    @SearchEmployee
    @CheckCompanyCoherence
    @Override
    public Employee updateEmployeeRoles(@CompanyId String companyId, @EmployeeId String employeeId,
                                        @Valid EmployeeUpdatedRoles employeeUpdatedRoles) {
        Employee employee = SearchingAspect.EMPLOYEE_FOUND.get();

        Set<String> rolesToRemove = Sets.newHashSet(employeeUpdatedRoles.rolesToRemove());
        Set<String> rolesToAdd = Sets.newHashSet(employeeUpdatedRoles.rolesToAdd());

        employee.getRoles().removeIf(r -> rolesToRemove.contains(r.getId()));

        List<Role> newRoles = roleRepository.findByIdIn(rolesToAdd);
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
            employee.getAssignedEstablishments().add(establishment.summary());

            employeeRepository.save(employee);
            establishmentRepository.save(establishment);

            return true;
        } else {
            // Just in case employee is not up-to-date
            EstablishmentSummary establishmentSummary = establishment.summary();
            boolean added = employee.getAssignedEstablishments().add(establishmentSummary);
            if (added) {
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
            employee.getAssignedEstablishments().remove(establishment.summary());

            employeeRepository.save(employee);
            establishmentRepository.save(establishment);
            return true;
        } else {
            // Just in case employee is not up-to-date
            EstablishmentSummary establishmentSummary = establishment.summary();
            boolean removed = employee.getAssignedEstablishments().remove(establishmentSummary);
            if (removed) {
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
        boolean activated = employee.getActive();
        if (!activated) {
            employee.setActive(true);
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
        boolean activated = employee.getActive();
        if (activated) {
            employee.setActive(false);
            employeeRepository.save(employee);
            return true;
        }
        return false;
    }
}

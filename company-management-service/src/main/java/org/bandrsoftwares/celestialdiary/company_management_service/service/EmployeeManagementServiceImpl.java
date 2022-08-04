package org.bandrsoftwares.celestialdiary.company_management_service.service;

import com.google.common.collect.Lists;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.bandrsoftwares.celestialdiary.aop.SearchingAspect;
import org.bandrsoftwares.celestialdiary.aop.company.CheckCompanyCoherence;
import org.bandrsoftwares.celestialdiary.aop.company.CompanyId;
import org.bandrsoftwares.celestialdiary.aop.company.SearchCompany;
import org.bandrsoftwares.celestialdiary.aop.employee.EmployeeId;
import org.bandrsoftwares.celestialdiary.aop.employee.SearchEmployee;
import org.bandrsoftwares.celestialdiary.model.mongodb.company.Company;
import org.bandrsoftwares.celestialdiary.model.mongodb.establishment.Establishment;
import org.bandrsoftwares.celestialdiary.model.mongodb.establishment.EstablishmentRepository;
import org.bandrsoftwares.celestialdiary.model.mongodb.person.employee.*;
import org.bandrsoftwares.celestialdiary.model.mongodb.saleable.prestation.Prestation;
import org.bandrsoftwares.celestialdiary.model.mongodb.saleable.prestation.PrestationRepository;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;
import java.time.Instant;
import java.util.List;

@Slf4j
@RequiredArgsConstructor
@Validated
@Service
public class EmployeeManagementServiceImpl implements EmployeeManagementService {

    // Variables.

    private final EmployeeRepository employeeRepository;
    private final PrestationRepository prestationRepository;
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
        List<Prestation> praticablePrestations =
                prestationRepository.findByCompanyAndIdIn(company, employeeCreationInformation.praticablePrestations());

        Employee employee = createEmployeeFrom(company, employeeCreationInformation, roles, praticablePrestations);
        return employeeRepository.insert(employee);
    }

    private Employee createEmployeeFrom(Company company, @Valid EmployeeCreationInformation employeeCreationInformation, List<Role> roles,
                                        List<Prestation> praticablePrestations) {
        return Employee.builder()
                .email(employeeCreationInformation.email())
                .password(employeeCreationInformation.password())
                .firstName(employeeCreationInformation.firstName())
                .lastName(employeeCreationInformation.lastName())
                .comment(employeeCreationInformation.comment())
                .gender(employeeCreationInformation.gender() != null ? employeeCreationInformation.gender() : PersonGender.NO_GENDER)
                .phone(employeeCreationInformation.phone())
                .praticablePrestations(praticablePrestations)
                .activated(true)
                .tags(employeeCreationInformation.tags())
                .roles(roles)
                .company(company)
                .creationDate(Instant.now())
                .build();
    }

    @SearchCompany
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

        employee.setComment(update.comment());

        if (update.gender() != null) {
            employee.setGender(update.gender());
        }

        if (update.phone() != null) {
            employee.setPhone(update.phone());
        } else {
            employee.setPhone(null);
        }

        if (update.tags() != null) {
            employee.setTags(update.tags());
        } else {
            employee.setTags(Lists.newArrayList());
        }

        if (update.praticablePrestations() != null && !update.praticablePrestations().isEmpty()) {
            List<Prestation> praticablePrestations = prestationRepository.findByCompanyAndIdIn(SearchingAspect.COMPANY_FOUND.get(),
                                                                                               update.praticablePrestations());
            employee.setPraticablePrestations(praticablePrestations);
        } else {
            employee.setPraticablePrestations(Lists.newArrayList());
        }

        return employeeRepository.save(employee);
    }

    @SearchCompany
    @SearchEmployee
    @CheckCompanyCoherence
    @Override
    public Employee updateEmployeeRoles(@CompanyId String companyId, @EmployeeId String employeeId,
                                        @Valid EmployeeUpdatedRoles roleUpdate) {
        Company company = SearchingAspect.COMPANY_FOUND.get();
        Employee employee = SearchingAspect.EMPLOYEE_FOUND.get();
        if (!roleUpdate.roles().isEmpty()) {
            List<Role> roles = roleRepository.findByCompanyAndIdIn(company, roleUpdate.roles());
            employee.setRoles(roles);

        } else {
            employee.setRoles(Lists.newArrayList());
        }

        return employeeRepository.save(employee);
    }

    @SearchCompany
    @SearchEmployee
    @CheckCompanyCoherence
    @Override
    public Employee updateEmployeeEstablishment(@CompanyId String companyId, @EmployeeId String employeeId,
                                                @Valid EmployeeEstablishmentInformation info) {
        Employee employee = SearchingAspect.EMPLOYEE_FOUND.get();
        List<Establishment> allEstablishments = establishmentRepository.findByCompany(SearchingAspect.COMPANY_FOUND.get());

        if (!info.establishmentIds().isEmpty()) {
            List<Establishment> chosenEstablishments =
                    establishmentRepository.findByCompanyAndIdIn(SearchingAspect.COMPANY_FOUND.get(), info.establishmentIds());
            employee.setAssignedEstablishments(chosenEstablishments);

            updateEstablishmentEmployeeAssignation(employee, allEstablishments, chosenEstablishments);
        } else {
            employee.setAssignedEstablishments(Lists.newArrayList());
            updateEstablishmentEmployeeAssignation(employee, allEstablishments, Lists.newArrayList());
        }

        return employeeRepository.save(employee);
    }

    private void updateEstablishmentEmployeeAssignation(Employee employee, List<Establishment> allEstablishments,
                                                        List<Establishment> chosenEstablishments) {
        List<String> chosenEstablishmentIds = chosenEstablishments.stream().map(Establishment::getId).toList();
        for (Establishment establishment : allEstablishments) {
            if (!chosenEstablishmentIds.contains(establishment.getId())) {
                // If establishment is not assign to the employee
                boolean removed = establishment.getAssignedEmployees().removeIf(emp -> emp.getId().equals(employee.getId()));
                if (removed) {
                    establishmentRepository.save(establishment);
                }
            } else {
                // If establishment is assign to the employee
                if (!establishment.getAssignedEmployees().stream().map(Employee::getId).toList().contains(employee.getId())) {
                    establishment.getAssignedEmployees().add(employee);
                    establishmentRepository.save(establishment);
                }
            }
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

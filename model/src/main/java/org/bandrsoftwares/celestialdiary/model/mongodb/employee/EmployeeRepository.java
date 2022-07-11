package org.bandrsoftwares.celestialdiary.model.mongodb.employee;

import org.bandrsoftwares.celestialdiary.model.mongodb.company.Company;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;
import java.util.Optional;

public interface EmployeeRepository extends MongoRepository<Employee, String> {

    List<Employee> findByCompanySummaryCompany(Company company);

    List<Employee> findByCompanySummaryCompanyAndActive(Company company, Boolean active);

    List<Employee> findByCompanySummaryCompanyAndIsTechnician(Company company, Boolean isTechnician);

    List<Employee> findByCompanySummaryCompanyAndActiveAndIsTechnician(Company company, Boolean active, Boolean isTechnician);

    Optional<Employee> findByCompanySummaryCompanyAndEmail(Company company, String email);
}

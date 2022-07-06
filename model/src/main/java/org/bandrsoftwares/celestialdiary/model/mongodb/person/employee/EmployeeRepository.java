package org.bandrsoftwares.celestialdiary.model.mongodb.person.employee;

import org.bandrsoftwares.celestialdiary.model.mongodb.company.Company;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface EmployeeRepository extends MongoRepository<Employee, String> {

    Optional<Employee> findByCompanySummaryCompanyAndEmail(Company company, String email);
}

package org.bandrsoftwares.celestialdiary.model.mongodb.employee;

import org.bandrsoftwares.celestialdiary.model.mongodb.company.Company;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;
import java.util.Optional;

public interface EmployeeRepository extends MongoRepository<Employee, String> {

    List<Employee> findByCompany(Company company);

    List<Employee> findByCompanyAndFirstNameRegexOrLastNameRegexOrEmailRegex(Company company, String firstName, String lastName, String email);

    Optional<Employee> findByCompanyAndEmail(Company company, String email);
}

package org.bandrsoftwares.celestialdiary.model.mongodb.employee;

import org.bandrsoftwares.celestialdiary.model.mongodb.company.Company;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;
import java.util.Optional;

public interface EmployeeRepository extends MongoRepository<Employee, String> {

    List<Employee> findByCompany(Company company);

    List<Employee> findByCompanyAndActivated(Company company, Boolean active);

    List<Employee> findByCompanyAndIsTechnician(Company company, Boolean isTechnician);

    List<Employee> findByCompanyAndActivatedAndIsTechnician(Company company, Boolean active, Boolean isTechnician);

    Optional<Employee> findByCompanyAndEmail(Company company, String email);
}

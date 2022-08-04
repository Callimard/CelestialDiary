package org.bandrsoftwares.celestialdiary.model.mongodb.person.employee;

import org.bandrsoftwares.celestialdiary.model.mongodb.company.Company;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface RoleRepository extends MongoRepository<Role, String> {

    List<Role> findByCompany(Company company);

    List<Role> findByCompanyAndIdIn(Company company, Iterable<String> ids);

    List<Role> findByCompanyAndNameRegex(Company company, String name);
}

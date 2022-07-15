package org.bandrsoftwares.celestialdiary.model.mongodb.employee;

import org.bandrsoftwares.celestialdiary.model.mongodb.company.Company;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface RoleRepository extends MongoRepository<Role, String> {

    List<Role> findByCompanyAndIdIn(Company company, Iterable<String> ids);
}

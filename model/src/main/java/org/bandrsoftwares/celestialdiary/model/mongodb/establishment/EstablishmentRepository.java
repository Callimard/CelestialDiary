package org.bandrsoftwares.celestialdiary.model.mongodb.establishment;

import org.bandrsoftwares.celestialdiary.model.mongodb.company.Company;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface EstablishmentRepository extends MongoRepository<Establishment, String> {

    List<Establishment> findByCompany(Company company);

    List<Establishment> findByCompanyAndIdIn(Company company, Iterable<String> ids);

    List<Establishment> findByCompanyAndNameRegex(Company company, String name);
}

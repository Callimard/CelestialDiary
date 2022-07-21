package org.bandrsoftwares.celestialdiary.model.mongodb.saleable.prestation;

import org.bandrsoftwares.celestialdiary.model.mongodb.company.Company;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface PrestationRepository extends MongoRepository<Prestation, String> {

    List<Prestation> findByCompany(Company company);

    List<Prestation> findByCompanyAndIdIn(Company company, Iterable<String> prestationIds);

    List<Prestation> findByCompanyAndNameRegex(Company company, String name);
}

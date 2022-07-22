package org.bandrsoftwares.celestialdiary.model.mongodb.saleable.bundle;

import org.bandrsoftwares.celestialdiary.model.mongodb.company.Company;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface BundleRepository extends MongoRepository<Bundle, String> {

    List<Bundle> findByCompany(Company company);

    List<Bundle> findByCompanyAndNameRegex(Company company, String name);
}

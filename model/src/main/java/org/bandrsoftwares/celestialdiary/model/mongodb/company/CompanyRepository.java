package org.bandrsoftwares.celestialdiary.model.mongodb.company;

import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface CompanyRepository extends MongoRepository<Company, String> {

    Optional<Company> findByEmail(String email);
}

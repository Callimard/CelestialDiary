package org.bandrsoftwares.celestialdiary.model.mongodb.person.client;

import org.bandrsoftwares.celestialdiary.model.mongodb.company.Company;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface ClientRepository extends MongoRepository<Client, String> {

    List<Client> findByCompany(Company company);

    List<Client> findByCompanyAndFirstNameRegexOrLastNameRegexOrEmailRegex(Company company, String firstName, String lastName, String email);
}

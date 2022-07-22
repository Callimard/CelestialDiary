package org.bandrsoftwares.celestialdiary.model.mongodb.saleable.product;

import org.bandrsoftwares.celestialdiary.model.mongodb.company.Company;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface ProductRepository extends MongoRepository<Product, String> {

    List<Product> findByCompany(Company company);

    List<Product> findByCompanyAndIdIn(Company company, Iterable<String> productIds);

    List<Product> findByCompanyAndNameRegex(Company company, String name);
}

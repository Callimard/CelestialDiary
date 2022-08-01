package org.bandrsoftwares.celestialdiary.model.mongodb.equipment;

import org.bandrsoftwares.celestialdiary.model.mongodb.company.Company;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface EquipmentRepository extends MongoRepository<Equipment, String> {

    List<Equipment> findByCompany(Company company);

    List<Equipment> findByCompanyAndIdIn(Company company, Iterable<String> ids);

    List<Equipment> findByCompanyAndNameRegex(Company company, String name);
}

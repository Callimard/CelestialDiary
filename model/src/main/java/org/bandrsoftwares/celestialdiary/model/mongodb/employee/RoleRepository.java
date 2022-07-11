package org.bandrsoftwares.celestialdiary.model.mongodb.employee;

import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface RoleRepository extends MongoRepository<Role, String> {

    List<Role> findByIdIn(Iterable<String> ids);
}

package org.bandrsoftwares.celestialdiary.model.mongodb.employee;

import lombok.*;
import org.bandrsoftwares.celestialdiary.model.mongodb.company.Company;
import org.bandrsoftwares.celestialdiary.model.mongodb.establishment.Establishment;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.DocumentReference;

import java.util.List;

@ToString
@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "Role")
public class Role {

    @Id
    private String id;

    private String name;
    private String description;
    private List<Privilege> privileges;

    @ToString.Exclude
    @DocumentReference(collection = "Company")
    private Company company;

    @ToString.Exclude
    @DocumentReference(collection = "Establishment")
    private List<Establishment> establishments;
}

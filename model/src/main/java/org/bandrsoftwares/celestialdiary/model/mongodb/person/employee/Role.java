package org.bandrsoftwares.celestialdiary.model.mongodb.person.employee;

import lombok.*;
import org.bandrsoftwares.celestialdiary.model.mongodb.company.Company;
import org.bandrsoftwares.celestialdiary.security.privilege.Privilege;
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

    @ToString.Exclude
    @DocumentReference(collection = "Company", lazy = true)
    private Company company;
    private List<Privilege> companyPrivileges;

    private List<EstablishmentRole> establishmentRoles;
}

package org.bandrsoftwares.celestialdiary.model.mongodb.person.employee;

import lombok.*;
import org.bandrsoftwares.celestialdiary.model.mongodb.establishment.Establishment;
import org.bandrsoftwares.celestialdiary.security.privilege.Privilege;
import org.springframework.data.mongodb.core.mapping.DocumentReference;

import java.util.List;

@ToString
@Builder
@Getter
@Setter
@AllArgsConstructor
public class EstablishmentRole {

    @ToString.Exclude
    @DocumentReference(collection = "Establishment")
    private Establishment establishment;

    private List<Privilege> establishmentPrivileges;

}

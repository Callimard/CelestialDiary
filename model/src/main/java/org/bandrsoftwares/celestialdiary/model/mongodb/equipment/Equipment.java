package org.bandrsoftwares.celestialdiary.model.mongodb.equipment;

import lombok.*;
import org.bandrsoftwares.celestialdiary.model.mongodb.company.Company;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.DocumentReference;

import java.time.Instant;

@ToString
@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "Equipment")
public class Equipment {

    @Id
    private String id;

    private String name;
    private String description;

    private String photo;

    @ToString.Exclude
    @DocumentReference(collection = "Company")
    private Company company;

    private Instant creationDate;
}

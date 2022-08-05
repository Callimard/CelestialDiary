package org.bandrsoftwares.celestialdiary.model.mongodb.saleable;

import lombok.*;
import org.bandrsoftwares.celestialdiary.model.mongodb.company.Company;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DocumentReference;

import java.time.Instant;

@ToString
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public abstract class Saleable {

    @Id
    private String id;

    private String name;
    private String description;

    private Double suggestedPrice;

    private Boolean activated;

    private Instant creationDate;

    @ToString.Exclude
    @DocumentReference(collection = "Company", lazy = true)
    private Company company;
}

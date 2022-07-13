package org.bandrsoftwares.celestialdiary.model.mongodb.saleable.prestation;

import lombok.*;
import org.bandrsoftwares.celestialdiary.model.mongodb.company.Company;
import org.bandrsoftwares.celestialdiary.model.mongodb.saleable.Saleable;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.Instant;

@ToString(callSuper = true)
@Getter
@Setter
@NoArgsConstructor
@Document(collection = "Prestation")
public class Prestation extends Saleable {

    // Variables.

    private Integer nbNeededTechnician;
    private Integer nbClient;

    private Integer suggestedExecutionTime;

    // Constructors.

    @Builder
    public Prestation(String id, String name, String description, Double suggestedPrice, Boolean activated, Instant creationDate,
                      Company company, Integer nbNeededTechnician, Integer nbClient,
                      Integer suggestedExecutionTime) {
        super(id, name, description, suggestedPrice, activated, creationDate, company);
        this.nbNeededTechnician = nbNeededTechnician;
        this.nbClient = nbClient;
        this.suggestedExecutionTime = suggestedExecutionTime;
    }
}

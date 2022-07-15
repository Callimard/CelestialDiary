package org.bandrsoftwares.celestialdiary.model.mongodb.saleable.product;

import lombok.*;
import org.bandrsoftwares.celestialdiary.model.mongodb.company.Company;
import org.bandrsoftwares.celestialdiary.model.mongodb.saleable.Saleable;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.Instant;

@ToString(callSuper = true)
@Getter
@Setter
@NoArgsConstructor
@Document(collection = "Product")
public class Product extends Saleable {

    // Constructors.

    @Builder
    public Product(String id, String name, String description, Double suggestedPrice, Boolean activated, Instant creationDate,
                   Company company) {
        super(id, name, description, suggestedPrice, activated, creationDate, company);
    }
}

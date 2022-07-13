package org.bandrsoftwares.celestialdiary.model.mongodb.saleable.bundle;

import lombok.*;
import org.bandrsoftwares.celestialdiary.model.mongodb.company.Company;
import org.bandrsoftwares.celestialdiary.model.mongodb.saleable.Saleable;
import org.bandrsoftwares.celestialdiary.model.mongodb.saleable.product.EmbeddedProduct;
import org.bandrsoftwares.celestialdiary.model.mongodb.saleable.prestation.EmbeddedPrestation;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.Instant;
import java.util.List;

@ToString(callSuper = true)
@Getter
@Setter
@NoArgsConstructor
@Document(collection = "Bundle")
public class Bundle extends Saleable {

    // Variables.

    private List<EmbeddedPrestation> prestations;
    private List<EmbeddedProduct> products;

    // Constructors.

    @Builder
    public Bundle(String id, String name, String description, Double suggestedPrice, Boolean activated, Instant creationDate,
                  Company company,
                  List<EmbeddedPrestation> prestations,
                  List<EmbeddedProduct> products) {
        super(id, name, description, suggestedPrice, activated, creationDate, company);
        this.prestations = prestations;
        this.products = products;
    }
}

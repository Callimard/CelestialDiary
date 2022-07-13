package org.bandrsoftwares.celestialdiary.model.mongodb.establishment;

import lombok.*;
import org.bandrsoftwares.celestialdiary.model.mongodb.saleable.bundle.Bundle;
import org.springframework.data.mongodb.core.mapping.DocumentReference;

@ToString
@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class EstablishmentBundle {

    private Double customPrice;
    private Boolean usingCustomPrice;

    @ToString.Exclude
    @DocumentReference(collection = "Bundle")
    private Bundle bundle;
}

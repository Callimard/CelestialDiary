package org.bandrsoftwares.celestialdiary.model.mongodb.establishment;

import lombok.*;
import org.bandrsoftwares.celestialdiary.model.mongodb.saleable.bundle.Bundle;
import org.springframework.data.mongodb.core.mapping.DocumentReference;

@ToString(callSuper = true)
@Getter
@Setter
@NoArgsConstructor
public class EstablishmentBundle extends EstablishmentSaleable {

    @ToString.Exclude
    @DocumentReference(collection = "Bundle", lazy = true)
    private Bundle bundle;

    @Builder
    public EstablishmentBundle(Double customPrice, Boolean usingCustomPrice,
                               Bundle bundle) {
        super(customPrice, usingCustomPrice);
        this.bundle = bundle;
    }
}

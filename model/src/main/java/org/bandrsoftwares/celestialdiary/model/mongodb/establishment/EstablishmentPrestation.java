package org.bandrsoftwares.celestialdiary.model.mongodb.establishment;

import lombok.*;
import org.bandrsoftwares.celestialdiary.model.mongodb.saleable.prestation.Prestation;
import org.springframework.data.mongodb.core.mapping.DocumentReference;

/**
 * Represents a {@link Prestation} which is proposed in an {@link Establishment}. The reference of the {@code Establishment} is not present because
 * this class is only use directly in the {@code Establishment} class.
 */
@ToString(callSuper = true)
@Getter
@Setter
@NoArgsConstructor
public class EstablishmentPrestation extends EstablishmentSaleable {

    @ToString.Exclude
    @DocumentReference(collection = "Prestation", lazy = true)
    private Prestation prestation;

    @Builder
    public EstablishmentPrestation(Double customPrice, Boolean usingCustomPrice,
                                   Prestation prestation) {
        super(customPrice, usingCustomPrice);
        this.prestation = prestation;
    }
}

package org.bandrsoftwares.celestialdiary.model.mongodb.establishment;

import lombok.*;
import org.bandrsoftwares.celestialdiary.model.mongodb.saleable.prestation.EmbeddedPrestation;
import org.bandrsoftwares.celestialdiary.model.mongodb.saleable.prestation.Prestation;

/**
 * Represents a {@link Prestation} which is proposed in an {@link Establishment}. The reference of the {@code Establishment} is not present because this
 * class is only use directly in the {@code Establishment} class.
 */
@ToString
@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class EstablishmentPrestation {

    private Double customPrice;
    private Boolean usingCustomPrice;

    private EmbeddedPrestation embeddedService;
}

package org.bandrsoftwares.celestialdiary.model.mongodb.saleable.prestation;

import lombok.*;
import org.springframework.data.mongodb.core.mapping.DocumentReference;

/**
 * Embedded some useful information of a {@link Prestation}. It's avoiding  to charge all the {@code Service} information.
 */
@Builder
@ToString
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class BundlePrestation {

    @ToString.Exclude
    @DocumentReference(collection = "Prestation", lazy = true)
    private Prestation prestation;

    private Integer quantity;
}

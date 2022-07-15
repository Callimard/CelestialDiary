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
public class EmbeddedPrestation {

    private String name;
    private String description;

    @ToString.Exclude
    @DocumentReference(collection = "Service")
    private Prestation prestation;

    // Methods.

    public static EmbeddedPrestation from(Prestation prestation) {
        return EmbeddedPrestation.builder()
                .name(prestation.getName())
                .description(prestation.getDescription())
                .prestation(prestation)
                .build();
    }
}

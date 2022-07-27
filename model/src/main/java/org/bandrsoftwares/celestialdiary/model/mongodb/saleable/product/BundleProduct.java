package org.bandrsoftwares.celestialdiary.model.mongodb.saleable.product;

import lombok.*;
import org.springframework.data.mongodb.core.mapping.DocumentReference;

/**
 * Embedded some useful information of a {@link Product}. It's avoiding  to charge all the {@code Product} information.
 */
@Builder
@ToString
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class BundleProduct {

    @ToString.Exclude
    @DocumentReference(collection = "Product")
    private Product product;

    private Integer quantity;
}

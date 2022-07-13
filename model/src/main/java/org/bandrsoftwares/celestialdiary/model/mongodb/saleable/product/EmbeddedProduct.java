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
public class EmbeddedProduct {

    private String name;
    private String description;

    @ToString.Exclude
    @DocumentReference(collection = "Product")
    private Product product;

    // Methods.

    public static EmbeddedProduct from(Product product) {
        return EmbeddedProduct.builder()
                .name(product.getName())
                .description(product.getDescription())
                .product(product)
                .build();
    }
}

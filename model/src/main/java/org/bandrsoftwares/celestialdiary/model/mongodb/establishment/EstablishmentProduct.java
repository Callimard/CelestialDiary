package org.bandrsoftwares.celestialdiary.model.mongodb.establishment;

import lombok.*;
import org.bandrsoftwares.celestialdiary.model.mongodb.saleable.product.Product;
import org.springframework.data.mongodb.core.mapping.DocumentReference;

/**
 * Represents a {@link Product} which is proposed in an {@link Establishment}. The reference of the {@code Establishment} is not present because this
 * class is only use directly in the {@code Establishment} class.
 */
@ToString(callSuper = true)
@Getter
@Setter
@NoArgsConstructor
public class EstablishmentProduct extends EstablishmentSaleable {

    private Integer remainingStock;

    @ToString.Exclude
    @DocumentReference(collection = "Product", lazy = true)
    private Product product;

    @Builder
    public EstablishmentProduct(Double customPrice, Boolean usingCustomPrice, Integer remainingStock,
                                Product product) {
        super(customPrice, usingCustomPrice);
        this.remainingStock = remainingStock;
        this.product = product;
    }
}

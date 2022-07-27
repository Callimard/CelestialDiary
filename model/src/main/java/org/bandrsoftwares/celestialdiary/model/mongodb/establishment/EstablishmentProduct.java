package org.bandrsoftwares.celestialdiary.model.mongodb.establishment;

import lombok.*;
import org.bandrsoftwares.celestialdiary.model.mongodb.saleable.product.BundleProduct;
import org.bandrsoftwares.celestialdiary.model.mongodb.saleable.product.Product;

/**
 * Represents a {@link Product} which is proposed in an {@link Establishment}. The reference of the {@code Establishment} is not present because this
 * class is only use directly in the {@code Establishment} class.
 */
@ToString
@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class EstablishmentProduct {

    private Double customPrice;
    private Boolean usingCustomPrice;

    private Integer remainingStock;

    private BundleProduct bundleProduct;
}

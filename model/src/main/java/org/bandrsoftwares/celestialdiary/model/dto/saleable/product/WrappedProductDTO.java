package org.bandrsoftwares.celestialdiary.model.dto.saleable.product;

import org.bandrsoftwares.celestialdiary.model.dto.saleable.WrappedSaleableDTO;
import org.bandrsoftwares.celestialdiary.model.mongodb.saleable.product.Product;

public class WrappedProductDTO extends WrappedSaleableDTO {

    // Constructors.

    public WrappedProductDTO() {
        super();
    }

    public WrappedProductDTO(Product product) {
        super(product);
    }
}

package org.bandrsoftwares.celestialdiary.model.dto.establishment;

import org.bandrsoftwares.celestialdiary.model.dto.saleable.product.WrappedProductDTO;
import org.bandrsoftwares.celestialdiary.model.mongodb.establishment.EstablishmentProduct;

public record EstablishmentProductDTO(double customPrice, boolean usingCustomPrice, int remainingStock, WrappedProductDTO product) {

    public EstablishmentProductDTO(EstablishmentProduct establishmentProduct) {
        this(establishmentProduct.getCustomPrice(), establishmentProduct.getUsingCustomPrice(), establishmentProduct.getRemainingStock(),
             new WrappedProductDTO(establishmentProduct.getProduct()));
    }
}

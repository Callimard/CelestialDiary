package org.bandrsoftwares.celestialdiary.model.dto.saleable.product;

import org.bandrsoftwares.celestialdiary.model.mongodb.saleable.product.BundleProduct;

public record BundleProductDTO(WrappedProductDTO product, Integer quantity) {

    public BundleProductDTO(BundleProduct bundleProduct) {
        this(new WrappedProductDTO(bundleProduct.getProduct()), bundleProduct.getQuantity());
    }
}

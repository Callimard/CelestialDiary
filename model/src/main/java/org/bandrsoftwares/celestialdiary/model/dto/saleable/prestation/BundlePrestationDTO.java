package org.bandrsoftwares.celestialdiary.model.dto.saleable.prestation;

import org.bandrsoftwares.celestialdiary.model.mongodb.saleable.prestation.BundlePrestation;

public record BundlePrestationDTO(WrappedPrestationDTO prestation, Integer quantity) {

    public BundlePrestationDTO(BundlePrestation bundlePrestation) {
        this(new WrappedPrestationDTO(bundlePrestation.getPrestation()), bundlePrestation.getQuantity());
    }
}

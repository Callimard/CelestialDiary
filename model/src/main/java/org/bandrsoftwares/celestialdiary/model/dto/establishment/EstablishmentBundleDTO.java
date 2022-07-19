package org.bandrsoftwares.celestialdiary.model.dto.establishment;

import org.bandrsoftwares.celestialdiary.model.dto.saleable.bundle.WrappedBundleDTO;
import org.bandrsoftwares.celestialdiary.model.mongodb.establishment.EstablishmentBundle;

public record EstablishmentBundleDTO(double customPrice, boolean usingCustomPrice, WrappedBundleDTO bundle) {

    public EstablishmentBundleDTO(EstablishmentBundle establishmentBundle) {
        this(establishmentBundle.getCustomPrice(), establishmentBundle.getUsingCustomPrice(), new WrappedBundleDTO(establishmentBundle.getBundle()));
    }
}

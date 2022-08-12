package org.bandrsoftwares.celestialdiary.model.dto.establishment;

import org.bandrsoftwares.celestialdiary.model.dto.saleable.prestation.WrappedPrestationDTO;
import org.bandrsoftwares.celestialdiary.model.mongodb.establishment.EstablishmentPrestation;

public record EstablishmentPrestationDTO(double customPrice, boolean usingCustomPrice, WrappedPrestationDTO prestation) {

    public EstablishmentPrestationDTO(EstablishmentPrestation establishmentPrestation) {
        this(establishmentPrestation.getCustomPrice(), establishmentPrestation.getUsingCustomPrice(),
             new WrappedPrestationDTO(establishmentPrestation.getPrestation()));
    }
}

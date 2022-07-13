package org.bandrsoftwares.celestialdiary.model.dto.establishment;

import org.bandrsoftwares.celestialdiary.model.general.Address;
import org.bandrsoftwares.celestialdiary.model.mongodb.establishment.Establishment;

public record WrappedEstablishmentDTO(String id, String name, String description, Address address, boolean activated) {

    public WrappedEstablishmentDTO(Establishment establishment) {
        this(establishment.getId(), establishment.getName(), establishment.getDescription(), establishment.getAddress(),
             establishment.getActivated());
    }
}

package org.bandrsoftwares.celestialdiary.model.dto.establishment;

import org.bandrsoftwares.celestialdiary.model.dto.equipment.EquipmentDTO;
import org.bandrsoftwares.celestialdiary.model.mongodb.establishment.EstablishmentEquipment;

public record EstablishmentEquipmentDTO(int quantity, int numberUnusable, EquipmentDTO equipment) {

    public EstablishmentEquipmentDTO(EstablishmentEquipment establishmentEquipment) {
        this(establishmentEquipment.getQuantity(), establishmentEquipment.getNumberUnusable(),
             new EquipmentDTO(establishmentEquipment.getEquipment()));
    }
}

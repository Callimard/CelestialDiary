package org.bandrsoftwares.celestialdiary.model.dto.establishment;

import org.bandrsoftwares.celestialdiary.model.mongodb.establishment.EstablishmentEquipment;

public record EstablishmentEquipmentDTO(String id, String equipmentId, String name, boolean available, String roomId, String photo) {

    public EstablishmentEquipmentDTO(EstablishmentEquipment establishmentEquipment) {
        this(establishmentEquipment.getId(),
             establishmentEquipment.getEquipmentId(),
             establishmentEquipment.getName(),
             establishmentEquipment.getAvailable(),
             establishmentEquipment.getRoomId(),
             establishmentEquipment.getPhoto());
    }
}

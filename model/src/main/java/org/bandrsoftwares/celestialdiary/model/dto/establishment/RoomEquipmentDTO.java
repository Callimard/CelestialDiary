package org.bandrsoftwares.celestialdiary.model.dto.establishment;

import lombok.NonNull;
import org.bandrsoftwares.celestialdiary.model.mongodb.establishment.RoomEquipment;

public record RoomEquipmentDTO(int weight, @NonNull String establishmentEquipmentId) {

    public RoomEquipmentDTO(RoomEquipment roomEquipment) {
        this(roomEquipment.getWeight(), roomEquipment.getEstablishmentEquipmentId());
    }
}

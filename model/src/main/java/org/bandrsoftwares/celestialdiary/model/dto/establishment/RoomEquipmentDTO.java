package org.bandrsoftwares.celestialdiary.model.dto.establishment;

import org.bandrsoftwares.celestialdiary.model.dto.equipment.EquipmentDTO;
import org.bandrsoftwares.celestialdiary.model.mongodb.establishment.RoomEquipment;

public record RoomEquipmentDTO(int weight, EquipmentDTO equipment) {

    public RoomEquipmentDTO(RoomEquipment roomEquipment) {
        this(roomEquipment.getWeight(), new EquipmentDTO(roomEquipment.getEquipment()));
    }
}

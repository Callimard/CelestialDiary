package org.bandrsoftwares.celestialdiary.model.dto.establishment;

import org.bandrsoftwares.celestialdiary.model.mongodb.establishment.Room;

import java.util.List;

public record RoomDTO(String name, int capacity, List<RoomEquipmentDTO> equipments) {

    public RoomDTO(Room room) {
        this(room.getName(), room.getCapacity(), room.getEquipments().stream().map(RoomEquipmentDTO::new).toList());
    }
}

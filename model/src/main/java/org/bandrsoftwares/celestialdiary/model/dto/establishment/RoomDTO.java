package org.bandrsoftwares.celestialdiary.model.dto.establishment;

import org.bandrsoftwares.celestialdiary.model.mongodb.establishment.Room;

import java.util.List;

public record RoomDTO(String name, int capacity, List<RoomEquipmentDTO> roomEquipments, String photo, boolean available) {

    public RoomDTO(Room room) {
        this(room.getName(),
             room.getCapacity(),
             room.getRoomEquipments().stream().map(RoomEquipmentDTO::new).toList(),
             room.getPhoto(),
             room.getAvailable());
    }
}

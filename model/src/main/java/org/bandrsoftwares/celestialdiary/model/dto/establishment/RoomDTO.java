package org.bandrsoftwares.celestialdiary.model.dto.establishment;

import org.bandrsoftwares.celestialdiary.model.mongodb.establishment.Room;

public record RoomDTO(String name, int capacity, String photo, boolean available) {

    public RoomDTO(Room room) {
        this(room.getName(), room.getCapacity(), room.getPhoto(),
             room.getAvailable());
    }
}

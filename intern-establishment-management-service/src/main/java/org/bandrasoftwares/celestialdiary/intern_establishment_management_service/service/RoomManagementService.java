package org.bandrasoftwares.celestialdiary.intern_establishment_management_service.service;

import lombok.NonNull;
import org.bandrsoftwares.celestialdiary.aop.company.CompanyId;
import org.bandrsoftwares.celestialdiary.aop.establishment.EstablishmentId;
import org.bandrsoftwares.celestialdiary.model.dto.establishment.RoomEquipmentDTO;
import org.bandrsoftwares.celestialdiary.model.mongodb.establishment.Room;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import javax.validation.Valid;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.util.List;

public interface RoomManagementService {

    List<Room> allRooms(@CompanyId String companyId, @EstablishmentId String establishmentId);

    List<Room> searchRoom(@CompanyId String companyId, @EstablishmentId String establishmentId, @NonNull String filter);

    Room getSpecificRoom(@CompanyId String companyId, @EstablishmentId String establishmentId, @NonNull String roomName);

    Room createRoom(@CompanyId String companyId, @EstablishmentId String establishmentId, @Valid RoomCreationInformation information);

    record RoomCreationInformation(@NotNull String name, @Min(1) int capacity) {
    }

    Room updateRoom(@CompanyId String companyId, @EstablishmentId String establishmentId,
                    @NonNull String roomName, @Valid RoomUpdatedInformation information);

    record RoomUpdatedInformation(@NotNull String name, @Min(1) int capacity,
                                  String photo, boolean available, @NotNull List<RoomEquipmentDTO> roomEquipments) {
    }

    void deleteRoom(@CompanyId String companyId, @EstablishmentId String establishmentId, @NonNull String roomName);

    @ResponseStatus(value = HttpStatus.NOT_FOUND)
    class RoomNotFoundException extends RuntimeException {
        public RoomNotFoundException(String roomName, String establishmentName) {
            super("Room with the name " + roomName + " does not exists in the establishment " + establishmentName);
        }
    }

    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    class RoomAlreadyExistsException extends RuntimeException {
        public RoomAlreadyExistsException(String roomName, String establishmentName) {
            super("A room with the name " + roomName + " already exists in the establishment " + establishmentName);
        }
    }
}

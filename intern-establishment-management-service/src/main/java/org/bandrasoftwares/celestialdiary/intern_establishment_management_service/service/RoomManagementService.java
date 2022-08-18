package org.bandrasoftwares.celestialdiary.intern_establishment_management_service.service;

import lombok.NonNull;
import org.bandrsoftwares.celestialdiary.aop.company.CompanyId;
import org.bandrsoftwares.celestialdiary.aop.establishment.EstablishmentId;
import org.bandrsoftwares.celestialdiary.model.dto.establishment.RoomEquipmentDTO;
import org.bandrsoftwares.celestialdiary.model.mongodb.establishment.Room;

import javax.validation.Valid;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.util.List;

public interface RoomManagementService {

    List<Room> getAllRooms(@CompanyId String companyId, @EstablishmentId String establishmentId);

    List<Room> searchRoom(@CompanyId String companyId, @EstablishmentId String establishmentId, @NonNull String filter);

    Room getSpecificRoom(@CompanyId String companyId, @EstablishmentId String establishmentId, @NonNull String roomName);

    Room createRoom(@CompanyId String companyId, @EstablishmentId String establishmentId, @Valid RoomCreationInformation information);

    record RoomCreationInformation(@NotNull String name, @Min(1) int capacity, @NotNull List<RoomEquipmentDTO> equipments) {
    }

    Room updateRoom(@CompanyId String companyId, @EstablishmentId String establishmentId,
                    @NonNull String roomName, @Valid RoomUpdatedInformation information);

    record RoomUpdatedInformation(@NotNull String name, @Min(1) int capacity, @NotNull List<RoomEquipmentDTO> equipments) {
    }

    boolean deleteRoom(@CompanyId String companyId, @EstablishmentId String establishmentId, @NonNull String roomName);
}

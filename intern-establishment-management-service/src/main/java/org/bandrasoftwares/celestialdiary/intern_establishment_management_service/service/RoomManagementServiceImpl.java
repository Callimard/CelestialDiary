package org.bandrasoftwares.celestialdiary.intern_establishment_management_service.service;

import com.google.common.collect.Lists;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.bandrsoftwares.celestialdiary.aop.SearchingAspect;
import org.bandrsoftwares.celestialdiary.aop.company.CheckCompanyCoherence;
import org.bandrsoftwares.celestialdiary.aop.company.CompanyId;
import org.bandrsoftwares.celestialdiary.aop.establishment.EstablishmentId;
import org.bandrsoftwares.celestialdiary.aop.establishment.SearchEstablishment;
import org.bandrsoftwares.celestialdiary.model.mongodb.establishment.Establishment;
import org.bandrsoftwares.celestialdiary.model.mongodb.establishment.EstablishmentRepository;
import org.bandrsoftwares.celestialdiary.model.mongodb.establishment.Room;
import org.bandrsoftwares.celestialdiary.model.mongodb.establishment.RoomEquipment;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;
import java.util.List;

@Slf4j
@RequiredArgsConstructor
@Validated
@Service
public class RoomManagementServiceImpl implements RoomManagementService {

    // Variables.

    private final EstablishmentRepository establishmentRepository;

    // Methods.

    @SearchEstablishment
    @CheckCompanyCoherence
    @Override
    public List<Room> allRooms(@CompanyId String companyId, @EstablishmentId String establishmentId) {
        Establishment establishment = SearchingAspect.ESTABLISHMENT_FOUND.get();
        return establishment.allRooms();
    }

    @SearchEstablishment
    @CheckCompanyCoherence
    @Override
    public List<Room> searchRoom(@CompanyId String companyId, @EstablishmentId String establishmentId, @NonNull String filter) {
        String regexFilter = "(?i).*" + filter + ".*";
        Establishment establishment = SearchingAspect.ESTABLISHMENT_FOUND.get();
        return establishment.searchRoom(regexFilter);
    }

    @SearchEstablishment
    @CheckCompanyCoherence
    @Override
    public Room getSpecificRoom(@CompanyId String companyId, @EstablishmentId String establishmentId, @NonNull String roomName) {
        Establishment establishment = SearchingAspect.ESTABLISHMENT_FOUND.get();

        Room room = establishment.getSpecificRoom(roomName);
        if (room != null) {
            return room;
        } else {
            throw new RoomNotFoundException(roomName, establishment.getName());
        }
    }

    @SearchEstablishment
    @CheckCompanyCoherence
    @Override
    public Room createRoom(@CompanyId String companyId, @EstablishmentId String establishmentId, @Valid RoomCreationInformation information) {
        Establishment establishment = SearchingAspect.ESTABLISHMENT_FOUND.get();

        Room room = createRoomFrom(information);

        if (establishment.addRoom(room)) {
            establishmentRepository.save(establishment);
            return room;
        } else {
            throw new RoomAlreadyExistsException(room.getName(), establishment.getName());
        }
    }

    private Room createRoomFrom(RoomCreationInformation information) {
        return Room.builder()
                .name(information.name())
                .capacity(information.capacity())
                .available(true)
                .roomEquipments(Lists.newArrayList())
                .build();
    }


    @SearchEstablishment
    @CheckCompanyCoherence
    @Override
    public Room updateRoom(@CompanyId String companyId, @EstablishmentId String establishmentId,
                           @NonNull String roomName, @Valid RoomUpdatedInformation information) {
        Establishment establishment = SearchingAspect.ESTABLISHMENT_FOUND.get();
        Room room = establishment.getSpecificRoom(roomName);
        if (room != null) {
            checkUpdatedRoomNameNotAlreadyUsed(information, establishment, room);
            updateRoom(establishment, information, room);
            establishmentRepository.save(establishment);
            return room;
        } else {
            throw new RoomNotFoundException(roomName, establishment.getName());
        }
    }

    private void checkUpdatedRoomNameNotAlreadyUsed(RoomUpdatedInformation information, Establishment establishment, Room room) {
        for (Room r : establishment.getRooms()) {
            if (r != room && r.getName().equals(information.name())) {
                throw new RoomAlreadyExistsException(information.name(), establishment.getName());
            }
        }
    }

    private void updateRoom(Establishment establishment, RoomUpdatedInformation information, Room room) {
        room.setName(information.name());
        room.setCapacity(information.capacity());
        room.setPhoto(information.photo());
        room.setAvailable(information.available());
        room.setRoomEquipments(
                information.roomEquipments().stream().map(dto -> {
                    if (establishment.establishmentEquipmentExists(dto.establishmentEquipmentId())) {
                        return new RoomEquipment(dto.weight(), dto.establishmentEquipmentId());
                    } else
                        throw new EstablishmentEquipmentManagementService.EstablishmentEquipmentNotFoundException(dto.establishmentEquipmentId(),
                                                                                                                  establishment.getName());
                }).toList());
    }

    @SearchEstablishment
    @CheckCompanyCoherence
    @Override
    public void deleteRoom(@CompanyId String companyId, @EstablishmentId String establishmentId, @NonNull String roomName) {
        Establishment establishment = SearchingAspect.ESTABLISHMENT_FOUND.get();
        if (establishment.deleteRoom(roomName)) {
            establishmentRepository.save(establishment);
        } else {
            throw new RoomNotFoundException(roomName, establishment.getName());
        }
    }
}

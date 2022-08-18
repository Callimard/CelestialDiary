package org.bandrasoftwares.celestialdiary.intern_establishment_management_service.service;

import com.google.common.collect.Lists;
import com.google.common.collect.Sets;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.bandrsoftwares.celestialdiary.aop.SearchingAspect;
import org.bandrsoftwares.celestialdiary.aop.company.CheckCompanyCoherence;
import org.bandrsoftwares.celestialdiary.aop.company.CompanyId;
import org.bandrsoftwares.celestialdiary.aop.establishment.EstablishmentId;
import org.bandrsoftwares.celestialdiary.aop.establishment.SearchEstablishment;
import org.bandrsoftwares.celestialdiary.model.dto.establishment.RoomEquipmentDTO;
import org.bandrsoftwares.celestialdiary.model.mongodb.equipment.Equipment;
import org.bandrsoftwares.celestialdiary.model.mongodb.equipment.EquipmentRepository;
import org.bandrsoftwares.celestialdiary.model.mongodb.establishment.Establishment;
import org.bandrsoftwares.celestialdiary.model.mongodb.establishment.EstablishmentRepository;
import org.bandrsoftwares.celestialdiary.model.mongodb.establishment.Room;
import org.bandrsoftwares.celestialdiary.model.mongodb.establishment.RoomEquipment;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;
import java.util.function.Function;

@Slf4j
@RequiredArgsConstructor
@Validated
@Service
public class RoomManagementServiceImpl implements RoomManagementService {

    // Variables.

    private final EstablishmentRepository establishmentRepository;
    private final EquipmentRepository equipmentRepository;

    // Methods.

    @SearchEstablishment
    @CheckCompanyCoherence
    @Override
    public List<Room> getAllRooms(@CompanyId String companyId, @EstablishmentId String establishmentId) {
        Establishment establishment = SearchingAspect.ESTABLISHMENT_FOUND.get();
        if (establishment.getRooms() != null)
            return establishment.getRooms().stream().toList();
        else
            return Lists.newArrayList();
    }

    @SearchEstablishment
    @CheckCompanyCoherence
    @Override
    public List<Room> searchRoom(@CompanyId String companyId, @EstablishmentId String establishmentId, @NonNull String filter) {
        String regexFilter = "(?i).*" + filter + ".*";
        Establishment establishment = SearchingAspect.ESTABLISHMENT_FOUND.get();
        if (establishment.getRooms() != null)
            return establishment.getRooms().stream().filter(room -> room.getName().matches(regexFilter)).toList();
        else
            return Lists.newArrayList();
    }

    @SearchEstablishment
    @CheckCompanyCoherence
    @Override
    public Room getSpecificRoom(@CompanyId String companyId, @EstablishmentId String establishmentId, @NonNull String roomName) {
        Establishment establishment = SearchingAspect.ESTABLISHMENT_FOUND.get();
        if (establishment.getRooms() != null) {
            return findRoom(roomName, establishment);
        } else
            return null;
    }

    private Room findRoom(String roomName, Establishment establishment) {
        List<Room> found = establishment.getRooms().stream().filter(room -> room.getName().equals(roomName)).toList();
        return !found.isEmpty() ? found.get(0) : null;
    }

    @SearchEstablishment
    @CheckCompanyCoherence
    @Override
    public Room createRoom(@CompanyId String companyId, @EstablishmentId String establishmentId, @Valid RoomCreationInformation information) {
        Establishment establishment = SearchingAspect.ESTABLISHMENT_FOUND.get();
        if (establishment.getRooms() == null) {
            establishment.setRooms(Sets.newHashSet());
        }

        Room room = createRoomFrom(information);

        boolean added = establishment.getRooms().add(room);
        if (added) {
            establishmentRepository.save(establishment);
            return room;
        } else {
            return null;
        }
    }

    private Room createRoomFrom(RoomCreationInformation information) {
        return Room.builder()
                .name(information.name())
                .capacity(information.capacity())
                .equipments(information.equipments().stream().map(transformToRoomEquipment()).toList())
                .build();
    }

    private Function<RoomEquipmentDTO, RoomEquipment> transformToRoomEquipment() {
        return dto -> {
            Optional<Equipment> opEquipment = equipmentRepository.findById(dto.equipment().id());
            if (opEquipment.isPresent()) {
                return new RoomEquipment(dto.weight(), opEquipment.get());
            } else
                throw new IllegalArgumentException("Unknown equipment id " + dto.equipment().id());
        };
    }

    @SearchEstablishment
    @CheckCompanyCoherence
    @Override
    public Room updateRoom(@CompanyId String companyId, @EstablishmentId String establishmentId,
                           @NonNull String roomName, @Valid RoomUpdatedInformation information) {
        Establishment establishment = SearchingAspect.ESTABLISHMENT_FOUND.get();
        Room room = findRoom(roomName, establishment);
        if (room != null) {
            updateRoom(information, room);
            establishmentRepository.save(establishment);
            return room;
        } else {
            return null;
        }
    }

    private void updateRoom(RoomUpdatedInformation information, Room room) {
        room.setName(information.name());
        room.setCapacity(information.capacity());
        room.setEquipments(information.equipments().stream().map(transformToRoomEquipment()).toList());
    }

    @SearchEstablishment
    @CheckCompanyCoherence
    @Override
    public boolean deleteRoom(@CompanyId String companyId, @EstablishmentId String establishmentId, @NonNull String roomName) {
        Establishment establishment = SearchingAspect.ESTABLISHMENT_FOUND.get();

        boolean removed = establishment.getRooms().removeIf(room -> room.getName().equals(roomName));

        if (removed) {
            establishmentRepository.save(establishment);
            return true;
        } else {
            return false;
        }
    }
}

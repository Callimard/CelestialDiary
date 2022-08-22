package org.bandrsoftwares.celestialdiary.model.mongodb.establishment;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.google.common.collect.Sets;
import lombok.*;
import lombok.extern.slf4j.Slf4j;
import org.bandrsoftwares.celestialdiary.model.general.Address;
import org.bandrsoftwares.celestialdiary.model.general.time.DatedTimeIntervalList;
import org.bandrsoftwares.celestialdiary.model.general.time.NonDatedTimeIntervalList;
import org.bandrsoftwares.celestialdiary.model.mongodb.company.Company;
import org.bandrsoftwares.celestialdiary.model.mongodb.equipment.Equipment;
import org.bandrsoftwares.celestialdiary.model.mongodb.person.employee.Employee;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.DocumentReference;

import java.time.Instant;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.UUID;

@Slf4j
@ToString
@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "Establishment")
public class Establishment {

    @Id
    private String id;

    private String name;
    private String description;

    private Address address;

    // Hours

    private NonDatedTimeIntervalList mondayOpening;
    private NonDatedTimeIntervalList tuesdayOpening;
    private NonDatedTimeIntervalList wednesdayOpening;
    private NonDatedTimeIntervalList thursdayOpening;
    private NonDatedTimeIntervalList fridayOpening;
    private NonDatedTimeIntervalList saturdayOpening;
    private NonDatedTimeIntervalList sundayOpening;

    private List<DatedTimeIntervalList> exceptionalOpening;
    private List<DatedTimeIntervalList> exceptionalClosing;

    // Establishments Saleable

    private List<EstablishmentProduct> proposedProducts;
    private List<EstablishmentPrestation> proposedPrestations;
    private List<EstablishmentBundle> proposedBundles;

    // Establishment Equipments

    // Map<InternEstablishmentEquipmentId, EstablishmentEquipment>
    private Map<String, EstablishmentEquipment> equipments;

    // Establishment Rooms

    private Set<Room> rooms;

    // Establishment Employees

    @ToString.Exclude
    @DocumentReference(collection = "Employee", lazy = true)
    private List<Employee> assignedEmployees;

    @ToString.Exclude
    @DocumentReference(collection = "Company", lazy = true)
    private Company company;

    private Boolean activated;

    private Instant creationDate;

    // Methods.

    private void prepareEquipments() {
        if (equipments == null) {
            equipments = Maps.newHashMap();
        }
    }

    public void addEstablishmentEquipment(Equipment equipment, int quantity) {
        prepareEquipments();
        for (int i = 0; i < quantity; i++) {
            EstablishmentEquipment establishmentEquipment = createEstablishmentEquipmentFor(equipment, i);
            equipments.put(establishmentEquipment.getId(), establishmentEquipment);
        }
    }

    private EstablishmentEquipment createEstablishmentEquipmentFor(Equipment equipment, int i) {
        return EstablishmentEquipment.builder()
                .id(UUID.randomUUID().toString().replace("-", ""))
                .equipmentId(equipment.getId())
                .name(equipment.getName() + " " + i)
                .available(true)
                .build();
    }

    public List<EstablishmentEquipment> allEstablishmentEquipments() {
        if (equipments != null) {
            return equipments.values().stream().toList();
        }

        return Lists.newArrayList();
    }

    public List<EstablishmentEquipment> searchEstablishmentEquipments(String regexFilter) {
        List<EstablishmentEquipment> establishmentEquipments = allEstablishmentEquipments();
        return establishmentEquipments.stream().filter(establishmentEquipment -> establishmentEquipment.getName().matches(regexFilter)).toList();
    }

    public EstablishmentEquipment getEstablishmentEquipment(@NonNull String establishmentEquipmentId) {
        if (equipments != null) {
            return equipments.get(establishmentEquipmentId);
        } else
            return null;
    }

    public boolean deleteEstablishmentEquipment(@NonNull String establishmentEquipmentId) {
        if (equipments != null) {
            return equipments.remove(establishmentEquipmentId) != null;
        }
        return false;
    }

    private void prepareRooms() {
        if (rooms == null) {
            rooms = Sets.newHashSet();
        }
    }

    public boolean addRoom(@NonNull Room room) {
        prepareRooms();
        return rooms.add(room);
    }

    public List<Room> allRooms() {
        if (rooms != null)
            return rooms.stream().toList();
        else
            return Lists.newArrayList();
    }

    public List<Room> searchRoom(String regexFilter) {
        if (rooms != null) {
            return rooms.stream().filter(room -> room.getName().matches(regexFilter)).toList();
        }

        return Lists.newArrayList();
    }

    public Room getSpecificRoom(@NonNull String roomName) {
        if (rooms != null) {
            List<Room> found = rooms.stream().filter(room -> room.getName().equals(roomName)).toList();
            return !found.isEmpty() ? found.get(0) : null;
        }

        return null;
    }

    public boolean deleteRoom(@NonNull String roomName) {
        if (rooms != null) {
            return rooms.removeIf(room -> room.getName().equals(roomName));
        }

        return false;
    }
}

package org.bandrsoftwares.celestialdiary.model.mongodb.establishment;

import lombok.*;
import lombok.extern.slf4j.Slf4j;
import org.bandrsoftwares.celestialdiary.model.general.Address;
import org.bandrsoftwares.celestialdiary.model.general.time.DatedTimeIntervalList;
import org.bandrsoftwares.celestialdiary.model.general.time.NonDatedTimeIntervalList;
import org.bandrsoftwares.celestialdiary.model.mongodb.company.Company;
import org.bandrsoftwares.celestialdiary.model.mongodb.person.employee.Employee;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.DocumentReference;

import java.time.Instant;
import java.util.List;
import java.util.Map;
import java.util.Set;

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

    // Map<EquipmentId, Map<InternEstablishmentEquipmentId, EstablishmentEquipment>>
    private Map<String, Map<String, EstablishmentEquipment>> equipments;

    // Establishment Rooms

    private Set<Room> rooms;

    @ToString.Exclude
    @DocumentReference(collection = "Employee", lazy = true)
    private List<Employee> assignedEmployees;

    @ToString.Exclude
    @DocumentReference(collection = "Company", lazy = true)
    private Company company;

    private Boolean activated;

    private Instant creationDate;

    // Methods.

    public EstablishmentEquipment getEstablishmentEquipment(@NonNull String equipmentId, @NonNull String establishmentEquipmentId) {
        if (getEquipments() != null) {
            Map<String, EstablishmentEquipment> map = getEquipments().get(equipmentId);
            if (map != null) {
                return map.get(establishmentEquipmentId);
            } else {
                return null;
            }
        } else
            return null;
    }

    public boolean deleteEstablishmentEquipment(@NonNull String equipmentId, @NonNull String establishmentEquipmentId) {
        if (getEquipments() != null) {
            Map<String, EstablishmentEquipment> map = getEquipments().get(equipmentId);
            if (map != null) {
                boolean removed = map.remove(establishmentEquipmentId) != null;
                if (map.isEmpty()) {
                    getEquipments().remove(equipmentId);
                }
                return removed;
            }
        }
        return false;
    }
}

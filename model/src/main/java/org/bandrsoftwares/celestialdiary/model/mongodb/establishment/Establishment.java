package org.bandrsoftwares.celestialdiary.model.mongodb.establishment;

import lombok.*;
import org.bandrsoftwares.celestialdiary.model.general.Address;
import org.bandrsoftwares.celestialdiary.model.general.time.DatedTimeIntervalList;
import org.bandrsoftwares.celestialdiary.model.general.time.NonDatedTimeIntervalList;
import org.bandrsoftwares.celestialdiary.model.mongodb.company.Company;
import org.bandrsoftwares.celestialdiary.model.mongodb.employee.Employee;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.DocumentReference;

import java.time.Instant;
import java.util.List;
import java.util.stream.Collectors;

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

    private NonDatedTimeIntervalList mondayOpening;
    private NonDatedTimeIntervalList tuesdayOpening;
    private NonDatedTimeIntervalList wednesdayOpening;
    private NonDatedTimeIntervalList thursdayOpening;
    private NonDatedTimeIntervalList fridayOpening;
    private NonDatedTimeIntervalList saturdayOpening;
    private NonDatedTimeIntervalList sundayOpening;

    private List<DatedTimeIntervalList> exceptionalOpening;
    private List<DatedTimeIntervalList> exceptionalClosing;

    private List<EstablishmentProduct> proposedProducts;
    private List<EstablishmentPrestation> proposedPrestations;
    private List<EstablishmentBundle> proposedBundles;

    @ToString.Exclude
    @DocumentReference(collection = "Employee")
    private List<Employee> assignedEmployees;

    @ToString.Exclude
    @DocumentReference(collection = "Company")
    private Company company;

    private Boolean activated;

    private Instant creationDate;

    // Methods.

    public boolean hasAsAssignedEmployee(Employee employee) {
        return getAssignedEmployees().stream().map(Employee::getId).collect(Collectors.toSet()).contains(employee.getId());
    }
}

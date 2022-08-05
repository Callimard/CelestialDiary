package org.bandrsoftwares.celestialdiary.model.mongodb.person.employee;

import lombok.*;
import lombok.extern.slf4j.Slf4j;
import org.bandrsoftwares.celestialdiary.model.general.time.NonDatedTimeIntervalList;
import org.bandrsoftwares.celestialdiary.model.mongodb.establishment.Establishment;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.DocumentReference;

@Slf4j
@ToString
@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "EmployeeWorkingHours")
public class EmployeeWorkingHours {

    @Id
    private String id;

    @NonNull
    private Integer year;
    @NonNull
    private Integer weekNumber;

    private NonDatedTimeIntervalList monday;
    private NonDatedTimeIntervalList tuesday;
    private NonDatedTimeIntervalList wednesday;
    private NonDatedTimeIntervalList thursday;
    private NonDatedTimeIntervalList friday;
    private NonDatedTimeIntervalList saturday;
    private NonDatedTimeIntervalList sunday;

    @ToString.Exclude
    @DocumentReference(collection = "Employee", lazy = true)
    @NonNull
    private Employee employee;

    @ToString.Exclude
    @DocumentReference(collection = "Establishment", lazy = true)
    @NonNull
    private Establishment establishment;
}

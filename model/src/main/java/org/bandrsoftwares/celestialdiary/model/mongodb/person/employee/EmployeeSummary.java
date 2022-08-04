package org.bandrsoftwares.celestialdiary.model.mongodb.person.employee;

import lombok.*;
import org.springframework.data.mongodb.core.mapping.DocumentReference;

@ToString
@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class EmployeeSummary {

    private String userEmail;
    private String userFirstName;
    private String userLastName;

    @ToString.Exclude
    @DocumentReference(collection = "Employee", lazy = true)
    private Employee employee;
}

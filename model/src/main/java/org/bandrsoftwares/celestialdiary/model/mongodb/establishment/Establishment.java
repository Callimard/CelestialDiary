package org.bandrsoftwares.celestialdiary.model.mongodb.establishment;

import lombok.*;
import org.bandrsoftwares.celestialdiary.model.mongodb.company.CompanySummary;
import org.bandrsoftwares.celestialdiary.model.mongodb.employee.Employee;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.DocumentReference;

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

    @ToString.Exclude
    @DocumentReference(collection = "Employee", lazy = true)
    private List<Employee> assignedEmployees;

    private CompanySummary companySummary;

    public EstablishmentSummary summary() {
        return EstablishmentSummary.builder()
                .establishmentName(name)
                .establishment(this)
                .build();
    }

    public boolean hasAsAssignedEmployee(Employee employee) {
        return getAssignedEmployees().stream().map(Employee::getId).collect(Collectors.toSet()).contains(employee.getId());
    }
}

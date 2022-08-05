package org.bandrsoftwares.celestialdiary.model.mongodb.company;

import lombok.*;
import org.bandrsoftwares.celestialdiary.model.general.Address;
import org.bandrsoftwares.celestialdiary.model.mongodb.person.employee.Employee;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.DocumentReference;

import java.time.Instant;
import java.util.List;

@ToString
@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "Company")
public class Company {

    @Id
    private String id;

    private String name;

    private String email;
    private String password;

    private String description;
    private Address address;
    private String phone;

    @ToString.Exclude
    @DocumentReference(collection = "Employee", lazy = true)
    private List<Employee> employees;

    private Instant creationDate;
}

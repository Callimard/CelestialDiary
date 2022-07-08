package org.bandrsoftwares.celestialdiary.model.mongodb.company;

import lombok.*;
import org.bandrsoftwares.celestialdiary.model.mongodb.general.Address;
import org.bandrsoftwares.celestialdiary.model.mongodb.employee.EmployeeSummary;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

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

    private EmployeeSummary manager;
    private List<EmployeeSummary> employees;

    private Instant creationDate;

    public CompanySummary summary() {
        return CompanySummary.builder()
                .companyEmail(email)
                .companyName(name)
                .companyCreationDate(creationDate)
                .company(this)
                .build();
    }
}

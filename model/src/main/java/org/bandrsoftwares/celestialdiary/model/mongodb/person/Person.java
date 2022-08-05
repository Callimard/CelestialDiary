package org.bandrsoftwares.celestialdiary.model.mongodb.person;

import lombok.*;
import org.bandrsoftwares.celestialdiary.model.mongodb.company.Company;
import org.bandrsoftwares.celestialdiary.model.mongodb.person.employee.PersonGender;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DocumentReference;

import java.time.Instant;

@ToString
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Person {

    @Id
    private String id;

    @NonNull
    private String email;

    @NonNull
    private String firstName;

    @NonNull
    private String lastName;

    private String comment;

    private String photo;

    private PersonGender gender;

    private String phone;

    @ToString.Exclude
    @DocumentReference(collection = "Company", lazy = true)
    @NonNull
    private Company company;

    @NonNull
    private Instant creationDate;
}

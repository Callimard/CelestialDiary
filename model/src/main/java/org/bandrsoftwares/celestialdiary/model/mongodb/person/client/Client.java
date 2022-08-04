package org.bandrsoftwares.celestialdiary.model.mongodb.person.client;

import lombok.*;
import lombok.extern.slf4j.Slf4j;
import org.bandrsoftwares.celestialdiary.model.mongodb.company.Company;
import org.bandrsoftwares.celestialdiary.model.mongodb.person.Person;
import org.bandrsoftwares.celestialdiary.model.mongodb.person.employee.PersonGender;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.Instant;

@Slf4j
@ToString(callSuper = true)
@Getter
@Setter
@NoArgsConstructor
@Document(collection = "Client")
public class Client extends Person {

    private String origin;

    private String technicalComment;

    @Builder
    public Client(String id, String email, String firstName, String lastName, String comment, String photo,
                  PersonGender gender, String phone,
                  Company company, Instant creationDate, String origin, String technicalComment) {
        super(id, email, firstName, lastName, comment, photo, gender, phone, company, creationDate);
        this.origin = origin;
        this.technicalComment = technicalComment;
    }
}

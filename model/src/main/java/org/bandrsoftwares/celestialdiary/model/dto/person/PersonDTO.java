package org.bandrsoftwares.celestialdiary.model.dto.person;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.bandrsoftwares.celestialdiary.model.mongodb.person.Person;
import org.bandrsoftwares.celestialdiary.model.mongodb.person.employee.PersonGender;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PersonDTO {

    private String id;
    private String email;
    private String firstName;
    private String lastName;
    private String comment;
    private String photo;
    private PersonGender gender;
    private String phone;
    private String creationDate;

    public PersonDTO(Person person) {
        this(person.getId(),
             person.getEmail(),
             person.getFirstName(),
             person.getLastName(),
             person.getComment(),
             person.getPhoto(),
             person.getGender(),
             person.getPhone(),
             person.getCreationDate().toString());
    }
}

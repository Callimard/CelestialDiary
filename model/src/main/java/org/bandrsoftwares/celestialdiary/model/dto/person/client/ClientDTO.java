package org.bandrsoftwares.celestialdiary.model.dto.person.client;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.bandrsoftwares.celestialdiary.model.dto.person.PersonDTO;
import org.bandrsoftwares.celestialdiary.model.mongodb.person.client.Client;

@Getter
@Setter
@NoArgsConstructor
public class ClientDTO extends PersonDTO {

    // Variables.

    private String origin;
    private String technicalComment;

    // Constructors.

    public ClientDTO(Client client) {
        super(client);
        this.origin = client.getOrigin();
        this.technicalComment = client.getTechnicalComment();
    }
}

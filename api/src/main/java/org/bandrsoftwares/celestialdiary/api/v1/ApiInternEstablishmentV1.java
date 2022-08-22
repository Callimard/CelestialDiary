package org.bandrsoftwares.celestialdiary.api.v1;

import static org.bandrsoftwares.celestialdiary.api.v1.ApiCompanyV1.ESTABLISHMENTS_URL;
import static org.bandrsoftwares.celestialdiary.api.v1.ApiCompanyV1.SPECIFIC_ESTABLISHMENT;

public class ApiInternEstablishmentV1 {

    private ApiInternEstablishmentV1() {
    }

    // Intern establishment Management.

    public static final String INTERN_ESTABLISHMENT_MANAGEMENT_URL = ESTABLISHMENTS_URL + SPECIFIC_ESTABLISHMENT;

    // Room Management.

    public static final String ROOMS_URL = INTERN_ESTABLISHMENT_MANAGEMENT_URL + "/rooms";
    public static final String SPECIFIC_ROOM = "/{roomName}";

    // Establishment Equipment Management.

    public static final String ESTABLISHMENT_EQUIPMENTS_URL = INTERN_ESTABLISHMENT_MANAGEMENT_URL + "/equipments";
    public static final String SPECIFIC_ESTABLISHMENT_EQUIPMENT = "/{idEstablishmentEquipment}";
}

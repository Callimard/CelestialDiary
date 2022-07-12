package org.bandrsoftwares.celestialdiary.api.v1;

import static org.bandrsoftwares.celestialdiary.api.v1.ApiV1.API_V1;

public class ApiCompanyV1 {

    private ApiCompanyV1() {
    }

    // Company Management API.

    public static final String COMPANIES_URL = API_V1 + "/companies";

    public static final String SPECIFIC_COMPANY_URL = COMPANIES_URL + "/{idCompany}";

    // Employee Management.

    public static final String EMPLOYEES_URL = SPECIFIC_COMPANY_URL + "/employees";

    public static final String SPECIFIC_EMPLOYEE = "/{idEmployee}";
    public static final String SPECIFIC_EMPLOYEE_ACTIVATION = SPECIFIC_EMPLOYEE + "/activation";
    public static final String SPECIFIC_EMPLOYEE_ROLES = SPECIFIC_EMPLOYEE + "/roles";
    public static final String SPECIFIC_EMPLOYEE_ESTABLISHMENT_ASSIGNATION = SPECIFIC_EMPLOYEE + "/assignation" + "/{idEstablishment}";

    // Establishment Management.

    public static final String ESTABLISHMENT_URL = SPECIFIC_COMPANY_URL + "/establishments";

    public static final String SPECIFIC_ESTABLISHMENT = "/{idEstablishment}";
    public static final String SPECIFIC_ESTABLISHMENT_ACTIVATION = SPECIFIC_ESTABLISHMENT + "/activation";
}

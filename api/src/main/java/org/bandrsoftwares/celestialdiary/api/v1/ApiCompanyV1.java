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
    public static final String SPECIFIC_EMPLOYEE_WORKING_HOURS = SPECIFIC_EMPLOYEE + "/working-hours/{idEstablishment}";
    public static final String SPECIFIC_EMPLOYEE_ACTIVATION = SPECIFIC_EMPLOYEE + "/activation";
    public static final String SPECIFIC_EMPLOYEE_ROLES = SPECIFIC_EMPLOYEE + "/roles";
    public static final String SPECIFIC_EMPLOYEE_ESTABLISHMENT_ASSIGNATION = SPECIFIC_EMPLOYEE + "/assignation";

    // Client Management.

    public static final String CLIENTS_URL = SPECIFIC_COMPANY_URL + "/clients";

    public static final String SPECIFIC_CLIENT = "/{idClient}";

    // Establishment Management.

    public static final String ESTABLISHMENTS_URL = SPECIFIC_COMPANY_URL + "/establishments";

    public static final String SPECIFIC_ESTABLISHMENT = "/{idEstablishment}";
    public static final String SPECIFIC_ESTABLISHMENT_ACTIVATION = SPECIFIC_ESTABLISHMENT + "/activation";

    // Product Management.

    public static final String PRODUCTS_URL = SPECIFIC_COMPANY_URL + "/products";
    public static final String SPECIFIC_PRODUCT = "/{idProduct}";
    public static final String SPECIFIC_PRODUCT_ACTIVATION = SPECIFIC_PRODUCT + "/activation";

    // Prestation Management.

    public static final String PRESTATIONS_URL = SPECIFIC_COMPANY_URL + "/prestations";

    public static final String SPECIFIC_PRESTATION = "/{idPrestation}";
    public static final String SPECIFIC_PRESTATION_ACTIVATION = SPECIFIC_PRESTATION + "/activation";

    // Bundle Management.

    public static final String BUNDLES_URL = SPECIFIC_COMPANY_URL + "/bundles";

    public static final String SPECIFIC_BUNDLE = "/{idBundle}";
    public static final String SPECIFIC_BUNDLE_ACTIVATION = SPECIFIC_BUNDLE + "/activation";

    // Role Management.

    public static final String ROLES_URL = SPECIFIC_COMPANY_URL + "/roles";

    public static final String SPECIFIC_ROLE = "/{idRole}";

    // Equipment Management.

    public static final String EQUIPMENT_URL = SPECIFIC_COMPANY_URL + "/equipments";

    public static final String SPECIFIC_EQUIPMENT = "/{idEquipment}";
}

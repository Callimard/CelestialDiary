package org.bandrsoftwares.celestialdiary.api.v1;

import static org.bandrsoftwares.celestialdiary.api.v1.ApiV1.API_V1;

public class AuthenticationV1 {

    public static final String V1_AUTHENTICATION = API_V1 + "/authentication";

    public static final String COMPANY_AUTHENTICATION = "/company/token";
    public static final String COMPANY_AUTHENTICATION_URL = V1_AUTHENTICATION + COMPANY_AUTHENTICATION;
    public static final String COMPANY_TOKEN_REFRESH = COMPANY_AUTHENTICATION + "/refresh";
    public static final String COMPANY_TOKEN_REFRESH_URL = COMPANY_AUTHENTICATION_URL + "/refresh";

    public static final String EMPLOYEE_AUTHENTICATION = "/employee/token";
    public static final String EMPLOYEE_AUTHENTICATION_URL = V1_AUTHENTICATION + EMPLOYEE_AUTHENTICATION;
    public static final String EMPLOYEE_TOKEN_REFRESH = EMPLOYEE_AUTHENTICATION + "/refresh";
    public static final String EMPLOYEE_TOKEN_REFRESH_URL = EMPLOYEE_AUTHENTICATION_URL + "/refresh";


    private AuthenticationV1() {
    }
}

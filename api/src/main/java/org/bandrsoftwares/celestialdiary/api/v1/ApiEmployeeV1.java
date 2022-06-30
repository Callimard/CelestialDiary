package org.bandrsoftwares.celestialdiary.api.v1;

import static org.bandrsoftwares.celestialdiary.api.v1.ApiV1.API_V1;

public class ApiEmployeeV1 {

    public static final String V1_EMPLOYEE = API_V1 + "/employee";

    public static final String TOKEN = "/token";
    public static final String V1_EMPLOYEE_TOKEN_URL = V1_EMPLOYEE + TOKEN;

    public static final String TOKEN_REFRESH = TOKEN + "/refresh";
    public static final String V1_EMPLOYEE_TOKEN_REFRESH_URL = V1_EMPLOYEE + TOKEN_REFRESH;

    private ApiEmployeeV1() {
    }
}

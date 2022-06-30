package org.bandrsoftwares.celestialdiary.api.v1;

import static org.bandrsoftwares.celestialdiary.api.v1.ApiV1.API_V1;

public class ApiCompanyV1 {

    public static final String V1_COMPANY = API_V1 + "/company";

    public static final String TOKEN = "/token";
    public static final String V1_COMPANY_TOKEN_URL = V1_COMPANY + TOKEN;

    public static final String TOKEN_REFRESH = TOKEN + "/refresh";
    public static final String V1_COMPANY_TOKEN_REFRESH_URL = V1_COMPANY + TOKEN_REFRESH;

    private ApiCompanyV1() {
    }
}

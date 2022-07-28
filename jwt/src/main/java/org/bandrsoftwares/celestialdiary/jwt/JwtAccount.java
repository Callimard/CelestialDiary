package org.bandrsoftwares.celestialdiary.jwt;

import lombok.*;

import java.util.List;
import java.util.Map;

@ToString
@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class JwtAccount {

    // Constants.

    public static final String CLAIM_ACCOUNT = "account";

    public static final String CLAIM_COMPANY_ID = "companyId";
    public static final String CLAIM_COMPANY_EMAIL = "companyEmail";
    public static final String CLAIM_COMPANY_NAME = "companyName";
    public static final String CLAIM_EMPLOYEE_EMAIL = "employeeEmail";
    public static final String CLAIM_EMPLOYEE_FIRST_NAME = "employeeFirstName";
    public static final String CLAIM_EMPLOYEE_LAST_NAME = "employeeLastName";
    public static final String CLAIM_ACCOUNT_AUTHORITIES = "accountAuthorities";

    // Variables.

    private String companyId;
    private String companyEmail;
    private String companyName;
    private String employeeEmail;
    private String employeeFirstName;
    private String employeeLastName;

    private List<String> companyPrivilegeIdentifiers;

    /* Map<EstablishmentId, List<EstablishmentPrivilegeIdentifier> */
    private Map<String, List<String>> establishmentPrivilegeIdentifiers;
}

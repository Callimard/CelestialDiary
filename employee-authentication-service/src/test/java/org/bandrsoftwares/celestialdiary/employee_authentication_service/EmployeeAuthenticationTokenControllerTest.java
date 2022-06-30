package org.bandrsoftwares.celestialdiary.employee_authentication_service;

import com.auth0.jwt.JWTVerifier;
import org.bandrsoftwares.celestialdiary.model.mongodb.company.Company;
import org.bandrsoftwares.celestialdiary.model.mongodb.company.CompanyRepository;
import org.bandrsoftwares.celestialdiary.model.mongodb.person.employee.Employee;
import org.bandrsoftwares.celestialdiary.model.mongodb.person.employee.EmployeeRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpHeaders;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.bandrsoftwares.celestialdiary.api.v1.ApiEmployeeV1.V1_EMPLOYEE_TOKEN_REFRESH_URL;
import static org.bandrsoftwares.celestialdiary.api.v1.ApiEmployeeV1.V1_EMPLOYEE_TOKEN_URL;
import static org.bandrsoftwares.celestialdiary.utils.HttpUtils.basicAuthorization;
import static org.bandrsoftwares.celestialdiary.utils.HttpUtils.bearerAuthorization;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@Nested
@DisplayName("Employee Authentication")
class EmployeeAuthenticationTokenControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private JWTVerifier jwtVerifier;

    @MockBean
    private CompanyRepository companyRepository;

    @MockBean
    private EmployeeRepository employeeRepository;

    private final String correctCompanyId = "ajfhs546qsf46qs46";

    private final String correctEmployeeEmail = "correct-employee@hotmail.fr";
    private final String correctEmployeePassword = "correctEmployeePassword";

    @BeforeEach
    void setUp() {
        Company correctCompany = buildCorrectCompany();
        when(companyRepository.findById(correctCompanyId)).thenReturn(Optional.of(correctCompany));

        Employee correctEmployee = buildCorrectEmployee(correctCompany);
        when(employeeRepository.findByCompanySummaryCompanyAndEmail(correctCompany, correctEmployeeEmail)).thenReturn(Optional.of(correctEmployee));
    }

    private Employee buildCorrectEmployee(Company company) {
        return Employee.builder()
                .email(correctEmployeeEmail)
                .password(correctEmployeePassword)
                .companySummary(company.summary())
                .build();
    }

    private Company buildCorrectCompany() {
        return Company.builder()
                .id(correctCompanyId)
                .email("correct-company@hotmail.fr")
                .name("CorrectCompany")
                .build();
    }

    @Nested
    @DisplayName("Employee Basic Authentication")
    class BasicAuthentication {

        @Test
        @DisplayName(V1_EMPLOYEE_TOKEN_URL + " returns 200 and a correct JWT token if basic authorization is correct.")
        void tokenWithCorrectBasicAuthorization() throws Exception {
            String jwt = mockMvc.perform(post(V1_EMPLOYEE_TOKEN_URL).header(HttpHeaders.AUTHORIZATION, correctEmployeeAuthorization()))
                    .andDo(print())
                    .andExpect(status().isOk()).andReturn().getResponse().getContentAsString();
            assertThat(jwt).isNotNull().isNotBlank();
            assertDoesNotThrow(() -> jwtVerifier.verify(jwt));
        }

        @Test
        @DisplayName(V1_EMPLOYEE_TOKEN_URL + " returns 401 if employee password is not correct")
        void tokenWithWrongEmployeePassword() throws Exception {
            mockMvc.perform(post(V1_EMPLOYEE_TOKEN_URL).header(HttpHeaders.AUTHORIZATION, wrongEmployeePasswordAuthorization()))
                    .andDo(print())
                    .andExpect(status().isUnauthorized());
        }

        @Test
        @DisplayName(V1_EMPLOYEE_TOKEN_URL + " returns 401 if employee is unknown")
        void tokenWithUnknownEmployee() throws Exception {
            mockMvc.perform(post(V1_EMPLOYEE_TOKEN_URL).header(HttpHeaders.AUTHORIZATION, unknownEmployeeAuthorization()))
                    .andDo(print())
                    .andExpect(status().isUnauthorized());
        }

        @Test
        @DisplayName(V1_EMPLOYEE_TOKEN_URL + " returns 401 if company is unknown")
        void tokenWithUnknownCompany() throws Exception {
            mockMvc.perform(post(V1_EMPLOYEE_TOKEN_URL).header(HttpHeaders.AUTHORIZATION, unknownCompanyAuthorization()))
                    .andDo(print())
                    .andExpect(status().isUnauthorized());
        }

        @Test
        @DisplayName(V1_EMPLOYEE_TOKEN_URL + " returns 401 if credentials are mal formatted")
        void tokenWithMalFormattedCredentials() throws Exception {
            mockMvc.perform(post(V1_EMPLOYEE_TOKEN_URL).header(HttpHeaders.AUTHORIZATION, malFormattedCredentials()))
                    .andDo(print())
                    .andExpect(status().isUnauthorized());
        }

        @Test
        @DisplayName(V1_EMPLOYEE_TOKEN_URL + " returns 400 if it is not a Http Basic authorization")
        void tokenWithNonBasicHttpAuthorization() throws Exception {
            String jwt = getCorrectEmployeeJwt();
            mockMvc.perform(post(V1_EMPLOYEE_TOKEN_URL).header(HttpHeaders.AUTHORIZATION, bearerAuthorization(jwt)))
                    .andDo(print())
                    .andExpect(status().isBadRequest());
        }
    }

    @Nested
    @DisplayName("Employee Refresh Token")
    class RefreshToken {

        @Test
        @DisplayName(V1_EMPLOYEE_TOKEN_REFRESH_URL + " returns 200 and a correct new jwt token with a valid jwt")
        void tokenRefreshWithValidJwt() throws Exception {
            String jwt = getCorrectEmployeeJwt();

            String refreshedJwt = mockMvc.perform(post(V1_EMPLOYEE_TOKEN_REFRESH_URL).header(HttpHeaders.AUTHORIZATION, bearerAuthorization(jwt)))
                    .andDo(print())
                    .andExpect(status().isOk())
                    .andReturn().getResponse().getContentAsString();
            assertThat(refreshedJwt).isNotNull().isNotBlank().isNotEqualTo(jwt);
            assertDoesNotThrow(() -> jwtVerifier.verify(refreshedJwt));

            mockMvc.perform(post(V1_EMPLOYEE_TOKEN_REFRESH_URL).header(HttpHeaders.AUTHORIZATION, bearerAuthorization(refreshedJwt)))
                    .andExpect(status().isOk());
        }

        @Test
        @DisplayName(V1_EMPLOYEE_TOKEN_REFRESH_URL + " returns 400 if it is not a Http Bearer authorization")
        void tokenRefreshWithNonBearerHttpAuthorization() throws Exception {
            mockMvc.perform(post(V1_EMPLOYEE_TOKEN_REFRESH_URL).header(HttpHeaders.AUTHORIZATION, correctEmployeeAuthorization()))
                    .andDo(print())
                    .andExpect(status().isBadRequest());
        }
    }

    private String getCorrectEmployeeJwt() throws Exception {
        return mockMvc.perform(post(V1_EMPLOYEE_TOKEN_URL).header(HttpHeaders.AUTHORIZATION, correctEmployeeAuthorization())).andReturn()
                .getResponse()
                .getContentAsString();
    }

    private String malFormattedCredentials() {
        return basicAuthorization(correctCompanyId + "unknown" + "+" + correctEmployeeEmail, correctEmployeePassword);
    }

    private String unknownCompanyAuthorization() {
        return basicAuthorization(correctCompanyId + "unknown" + "|" + correctEmployeeEmail, correctEmployeePassword);
    }

    private String unknownEmployeeAuthorization() {
        return basicAuthorization(correctCompanyId + "|" + correctEmployeeEmail + "unknown", correctEmployeePassword);
    }

    private String correctEmployeeAuthorization() {
        return basicAuthorization(correctCompanyId + "|" + correctEmployeeEmail, correctEmployeePassword);
    }

    private String wrongEmployeePasswordAuthorization() {
        return basicAuthorization(correctCompanyId + "|" + correctEmployeeEmail, correctEmployeePassword + "wrong");
    }
}

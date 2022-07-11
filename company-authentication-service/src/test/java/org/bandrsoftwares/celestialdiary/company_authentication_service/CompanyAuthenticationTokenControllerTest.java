package org.bandrsoftwares.celestialdiary.company_authentication_service;

import com.auth0.jwt.JWTVerifier;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.bandrsoftwares.celestialdiary.jwt.JwtTokenResponse;
import org.bandrsoftwares.celestialdiary.model.mongodb.company.Company;
import org.bandrsoftwares.celestialdiary.model.mongodb.company.CompanyRepository;
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

import java.time.Instant;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.bandrsoftwares.celestialdiary.api.v1.ApiCompanyV1.V1_COMPANY_TOKEN_REFRESH_URL;
import static org.bandrsoftwares.celestialdiary.api.v1.ApiCompanyV1.V1_COMPANY_TOKEN_URL;
import static org.bandrsoftwares.celestialdiary.utils.http.HttpTool.basicAuthorization;
import static org.bandrsoftwares.celestialdiary.utils.http.HttpTool.bearerAuthorization;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@Nested
@DisplayName("Company Authentication tests")
public class CompanyAuthenticationTokenControllerTest {

    @Autowired
    private ObjectMapper mapper;

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private JWTVerifier jwtVerifier;

    @MockBean
    private CompanyRepository companyRepository;

    private final String correctCompanyEmail = "correct-company@hotmail.fr";
    private final String correctCompanyPassword = "correctCompanyPassword";

    @BeforeEach
    void setUp() {
        Company correctCompany = Company.builder()
                .email(correctCompanyEmail)
                .password(correctCompanyPassword)
                .name("Correct company")
                .creationDate(Instant.now())
                .build();

        when(companyRepository.findByEmail(correctCompanyEmail)).thenReturn(Optional.of(correctCompany));
    }

    @Nested
    @DisplayName("Company Basic Authentication")
    class BasicAuthentication {

        @Test
        @DisplayName(V1_COMPANY_TOKEN_URL + " returns 200 and a correct JWT token if basic authorization is correct")
        void tokenWithCorrectBasicAuthorization() throws Exception {
            String response = mockMvc.perform(post(V1_COMPANY_TOKEN_URL).header(HttpHeaders.AUTHORIZATION, correctCompanyAuthorization()))
                    .andDo(print())
                    .andExpect(status().isOk()).andReturn().getResponse().getContentAsString();

            String jwt = extractJwtResponse(response).jwt();
            assertThat(jwt).isNotNull().isNotBlank();
            assertDoesNotThrow(() -> jwtVerifier.verify(jwt));
        }

        @Test
        @DisplayName(V1_COMPANY_TOKEN_URL + " returns 401 if company password is not correct")
        void tokenWithWrongPassword() throws Exception {
            mockMvc.perform(post(V1_COMPANY_TOKEN_URL).header(HttpHeaders.AUTHORIZATION, wrongCompanyPasswordAuthorization()))
                    .andDo(print())
                    .andExpect(status().isUnauthorized());
        }

        @Test
        @DisplayName(V1_COMPANY_TOKEN_URL + " returns 401 if company is unknown")
        void tokenWithUnknownCompany() throws Exception {
            mockMvc.perform(post(V1_COMPANY_TOKEN_URL).header(HttpHeaders.AUTHORIZATION, unknownCompanyAuthorization()))
                    .andDo(print())
                    .andExpect(status().isUnauthorized());
        }

        @Test
        @DisplayName(V1_COMPANY_TOKEN_URL + " returns 400 if if is not a Http Basic authorization")
        void tokenWithNonBasicHttpAuthorization() throws Exception {
            String jwt = getCorrectCompanyJwt();

            mockMvc.perform(post(V1_COMPANY_TOKEN_URL).header(HttpHeaders.AUTHORIZATION, bearerAuthorization(jwt)))
                    .andDo(print())
                    .andExpect(status().isBadRequest());
        }
    }

    @Nested
    @DisplayName("Company Refresh Token")
    class RefreshToken {

        @Test
        @DisplayName(V1_COMPANY_TOKEN_REFRESH_URL + " returns 200 and a correct new jwt token with a valid jwt")
        void tokenRefreshWithValidJwt() throws Exception {
            String jwt = getCorrectCompanyJwt();

            String response = mockMvc.perform(post(V1_COMPANY_TOKEN_REFRESH_URL).header(HttpHeaders.AUTHORIZATION, bearerAuthorization(jwt)))
                    .andDo(print())
                    .andExpect(status().isOk())
                    .andReturn().getResponse().getContentAsString();

            String refreshedJwt = extractJwtResponse(response).jwt();
            assertThat(refreshedJwt).isNotNull().isNotBlank().isNotEqualTo(jwt);
            assertDoesNotThrow(() -> jwtVerifier.verify(refreshedJwt));

            mockMvc.perform(post(V1_COMPANY_TOKEN_REFRESH_URL).header(HttpHeaders.AUTHORIZATION, bearerAuthorization(refreshedJwt)))
                    .andExpect(status().isOk());

        }

        @Test
        @DisplayName(V1_COMPANY_TOKEN_REFRESH_URL + " returns 400 if it is not a Http Bearer authorization")
        void tokenRefreshWithNonBearerHttpAuthorization() throws Exception {
            mockMvc.perform(post(V1_COMPANY_TOKEN_REFRESH_URL).header(HttpHeaders.AUTHORIZATION, correctCompanyAuthorization()))
                    .andDo(print())
                    .andExpect(status().isBadRequest());
        }
    }

    private JwtTokenResponse extractJwtResponse(String response) throws JsonProcessingException {
        return mapper.readValue(response, JwtTokenResponse.class);
    }

    private String getCorrectCompanyJwt() throws Exception {
        String resp = mockMvc.perform(post(V1_COMPANY_TOKEN_URL).header(HttpHeaders.AUTHORIZATION, correctCompanyAuthorization()))
                .andReturn().getResponse().getContentAsString();
        return extractJwtResponse(resp).jwt();
    }

    private String unknownCompanyAuthorization() {
        return basicAuthorization(correctCompanyEmail + "unknown", correctCompanyPassword);
    }

    private String correctCompanyAuthorization() {
        return basicAuthorization(correctCompanyEmail, correctCompanyPassword);
    }

    private String wrongCompanyPasswordAuthorization() {
        return basicAuthorization(correctCompanyEmail, correctCompanyPassword + "wrong");
    }
}

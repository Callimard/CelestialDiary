package org.bandrsoftwares.celestialdiary.company_management_service.service;

import org.assertj.core.util.Lists;
import org.bandrsoftwares.celestialdiary.aop.company.CompanyCoherenceException;
import org.bandrsoftwares.celestialdiary.aop.company.CompanyNotFoundException;
import org.bandrsoftwares.celestialdiary.aop.establishment.EstablishmentNotFoundException;
import org.bandrsoftwares.celestialdiary.model.mongodb.company.Company;
import org.bandrsoftwares.celestialdiary.model.mongodb.company.CompanyRepository;
import org.bandrsoftwares.celestialdiary.model.mongodb.establishment.Establishment;
import org.bandrsoftwares.celestialdiary.model.mongodb.establishment.EstablishmentRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import javax.validation.ConstraintViolationException;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

@Nested
@DisplayName("EstablishmentManagementService tests")
@SpringBootTest
class EstablishmentManagementPrestationImplTest {

    @Autowired
    private EstablishmentManagementServiceImpl establishmentManagementService;

    @MockBean
    private CompanyRepository companyRepository;

    @MockBean
    private EstablishmentRepository establishmentRepository;

    private final String correctCompanyId = "dsQ3GH4Z5H43ZHWGH4HG4Q5G333";
    private final String correctEstablishmentId = "qgq1g2qs1g4qg54q654zaenxwc24z4";
    private final String incoherentEstablishmentId = "qgq1g2qs1g4qg54q654zaenxwc2qgdsqgqdgq21g4dq654z4";

    @Mock
    private Establishment anyEstablishment;

    @BeforeEach
    void setup() {
        Company company = Company.builder()
                .id(correctCompanyId)
                .build();
        Company incoherentCompany = Company.builder()
                .id(correctCompanyId + "incoherent")
                .build();
        when(companyRepository.findById(correctCompanyId)).thenReturn(Optional.of(company));

        Establishment establishment = Establishment.builder()
                .id(correctEstablishmentId)
                .company(company)
                .build();
        Establishment incoherentEstablishment = Establishment.builder()
                .id(incoherentEstablishmentId)
                .company(incoherentCompany)
                .build();

        when(establishmentRepository.findById(correctEstablishmentId)).thenReturn(Optional.of(establishment));
        when(establishmentRepository.findById(incoherentEstablishmentId)).thenReturn(Optional.of(incoherentEstablishment));
        when(establishmentRepository.findByCompany(company)).thenReturn(Lists.newArrayList(anyEstablishment));
    }

    @Nested
    @DisplayName("allRegisteredEstablishment() tests")
    class AllRegisteredEstablishment {

        @Test
        @DisplayName("allRegisteredEstablishment() throws CompanyNotFoundException if the company is unknown")
        void withUnknownCompany() {
            assertThrows(CompanyNotFoundException.class, () -> establishmentManagementService.allRegisteredEstablishment(correctCompanyId + "wrong"));
        }

        @Test
        @DisplayName("allRegisteredEstablishment() returns correct list with known company")
        void withKnownCompany() {
            List<Establishment> establishments = establishmentManagementService.allRegisteredEstablishment(correctCompanyId);
            assertThat(establishments).isNotNull().isNotEmpty().contains(anyEstablishment);
        }
    }

    @Nested
    @DisplayName("createEstablishment() tests")
    class CreateEstablishment {

        @Test
        @DisplayName("createEstablishment() throws CompanyNotFoundException if the company is unknown")
        void withUnknownCompany() {
            EstablishmentManagementService.EstablishmentCreationInformation info = correctEstablishmentCreationInfo();
            assertThrows(CompanyNotFoundException.class, () -> establishmentManagementService.createEstablishment(correctCompanyId + "wrong", info));
        }

        @Test
        @DisplayName("createEstablishment() does not throw exception with known company")
        void withKnownCompany() {
            EstablishmentManagementService.EstablishmentCreationInformation info = correctEstablishmentCreationInfo();
            assertDoesNotThrow(() -> establishmentManagementService.createEstablishment(correctCompanyId, info));
        }

        @Test
        @DisplayName("createEstablishment() throws ConstraintValidation if specified info are not valid")
        void withInvalidInformation() {
            EstablishmentManagementService.EstablishmentCreationInformation incorrectInfo = incorrectEstablishmentCreationInfo();
            assertThrows(ConstraintViolationException.class, () -> establishmentManagementService.createEstablishment(correctCompanyId,
                                                                                                                      incorrectInfo));
        }

        private EstablishmentManagementService.EstablishmentCreationInformation correctEstablishmentCreationInfo() {
            return new EstablishmentManagementService.EstablishmentCreationInformation("Name", null, null, null, null, null, null, null, null, null);
        }

        private EstablishmentManagementService.EstablishmentCreationInformation incorrectEstablishmentCreationInfo() {
            return new EstablishmentManagementService.EstablishmentCreationInformation(null, null, null, null, null, null, null, null, null, null);
        }
    }

    @Nested
    @DisplayName("updateEstablishment() tests")
    class UpdateEstablishment {

        @Test
        @DisplayName("updateEstablishment() throws CompanyNotFoundException with unknown company")
        void withUnknownCompany() {
            EstablishmentManagementService.EstablishmentUpdatedInformation info = correctEstablishmentUpdateInfo();
            assertThrows(CompanyNotFoundException.class, () -> establishmentManagementService.updateEstablishment(correctCompanyId + "wrong",
                                                                                                                  correctEstablishmentId, info));
        }

        @Test
        @DisplayName("updateEstablishment() throws EstablishmentNotFoundException with unknown establishment")
        void withUnknownEstablishment() {
            EstablishmentManagementService.EstablishmentUpdatedInformation info = correctEstablishmentUpdateInfo();
            assertThrows(EstablishmentNotFoundException.class,
                         () -> establishmentManagementService.updateEstablishment(correctCompanyId, correctEstablishmentId + "wrong", info));
        }

        @Test
        @DisplayName("updateEstablishment() throws CompanyCoherenceException with incoherent establishment")
        void withIncoherentEstablishment() {
            EstablishmentManagementService.EstablishmentUpdatedInformation info = correctEstablishmentUpdateInfo();
            assertThrows(CompanyCoherenceException.class,
                         () -> establishmentManagementService.updateEstablishment(correctCompanyId, incoherentEstablishmentId, info));
        }

        private EstablishmentManagementService.EstablishmentUpdatedInformation correctEstablishmentUpdateInfo() {
            return new EstablishmentManagementService.EstablishmentUpdatedInformation("Name", null, null, null, null, null, null, null, null, null,
                                                                                      null, null);
        }
    }

    @Nested
    @DisplayName("activateEstablishment() tests")
    class ActivateEstablishment {
        @Test
        @DisplayName("activateEstablishment() throws CompanyNotFoundException with unknown company")
        void withUnknownCompany() {
            assertThrows(CompanyNotFoundException.class, () -> establishmentManagementService.activateEstablishment(correctCompanyId + "wrong",
                                                                                                                    correctEstablishmentId));
        }

        @Test
        @DisplayName("activateEstablishment() throws EstablishmentNotFoundException with unknown establishment")
        void withUnknownEstablishment() {
            assertThrows(EstablishmentNotFoundException.class,
                         () -> establishmentManagementService.activateEstablishment(correctCompanyId, correctEstablishmentId + "wrong"));
        }

        @Test
        @DisplayName("activateEstablishment() throws CompanyCoherenceException with incoherent establishment")
        void withIncoherentEstablishment() {
            assertThrows(CompanyCoherenceException.class,
                         () -> establishmentManagementService.activateEstablishment(correctCompanyId, incoherentEstablishmentId));
        }
    }

    @Nested
    @DisplayName("DeactivateEstablishment() tests")
    class DeactivateEstablishment {
        @Test
        @DisplayName("deactivateEstablishment() throws CompanyNotFoundException with unknown company")
        void withUnknownCompany() {
            assertThrows(CompanyNotFoundException.class, () -> establishmentManagementService.deactivateEstablishment(correctCompanyId + "wrong",
                                                                                                                      correctEstablishmentId));
        }

        @Test
        @DisplayName("deactivateEstablishment() throws EstablishmentNotFoundException with unknown establishment")
        void withUnknownEstablishment() {
            assertThrows(EstablishmentNotFoundException.class,
                         () -> establishmentManagementService.deactivateEstablishment(correctCompanyId, correctEstablishmentId + "wrong"));
        }

        @Test
        @DisplayName("deactivateEstablishment() throws CompanyCoherenceException with incoherent establishment")
        void withIncoherentEstablishment() {
            assertThrows(CompanyCoherenceException.class,
                         () -> establishmentManagementService.deactivateEstablishment(correctCompanyId, incoherentEstablishmentId));
        }
    }
}

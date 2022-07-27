package org.bandrsoftwares.celestialdiary.company_management_service.service;

import com.google.common.collect.Lists;
import com.google.common.collect.Sets;
import org.bandrsoftwares.celestialdiary.aop.company.CompanyCoherenceException;
import org.bandrsoftwares.celestialdiary.aop.company.CompanyNotFoundException;
import org.bandrsoftwares.celestialdiary.aop.employee.EmployeeNotFoundException;
import org.bandrsoftwares.celestialdiary.aop.establishment.EstablishmentNotFoundException;
import org.bandrsoftwares.celestialdiary.model.mongodb.company.Company;
import org.bandrsoftwares.celestialdiary.model.mongodb.company.CompanyRepository;
import org.bandrsoftwares.celestialdiary.model.mongodb.employee.Employee;
import org.bandrsoftwares.celestialdiary.model.mongodb.employee.EmployeeRepository;
import org.bandrsoftwares.celestialdiary.model.mongodb.employee.PersonGender;
import org.bandrsoftwares.celestialdiary.model.mongodb.establishment.Establishment;
import org.bandrsoftwares.celestialdiary.model.mongodb.establishment.EstablishmentRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.time.Instant;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

@Nested
@DisplayName("CompanyEmployeeManagementService tests")
@SpringBootTest
class EmployeeManagementPrestationImplTest {

    @MockBean
    private CompanyRepository companyRepository;

    @MockBean
    private EmployeeRepository employeeRepository;

    @MockBean
    private EstablishmentRepository establishmentRepository;

    @Autowired
    private EmployeeManagementServiceImpl employeeManagementService;

    private final String correctCompanyId = "qjhqglkqs65g23qsgq5f6dsg";

    private final String correctEmployeeId = "qjklghqjlgnq451ds2gh56sd4hs";
    private final String incoherentEmployeeId = "jkqhgjkqqsg454sdh45g4qd";
    private Employee correctEmployee;

    private final String correctEstablishmentId = "qjghjmqklsghmljqg456456q54q";
    private final String incoherentEstablishmentId = "qgkljhqgqdg45h6s456gds453";

    @BeforeEach
    void setup() {
        Company company = Company.builder()
                .id(correctCompanyId)
                .email("correct-company@hotmail.fr")
                .name("CorrectCompany")
                .creationDate(Instant.now())
                .build();

        Company wrongCompany = Company.builder()
                .id(correctCompanyId + "wrong")
                .email("correct-company@hotmail.fr")
                .name("CorrectCompany")
                .creationDate(Instant.now())
                .build();

        when(companyRepository.findById(correctCompanyId)).thenReturn(Optional.of(company));

        correctEmployee = Employee.builder()
                .company(company)
                .activated(true)
                .build();
        when(employeeRepository.findById(correctEmployeeId)).thenReturn(Optional.of(correctEmployee));

        Employee incoherentEmployee = Employee.builder()
                .company(wrongCompany)
                .activated(true)
                .build();
        when(employeeRepository.findById(incoherentEmployeeId)).thenReturn(Optional.of(incoherentEmployee));

        Establishment establishment = Establishment.builder()
                .company(company)
                .build();
        when(establishmentRepository.findById(correctEstablishmentId)).thenReturn(Optional.of(establishment));

        Establishment incoherentEstablishment = Establishment.builder()
                .company(wrongCompany)
                .build();
        when(establishmentRepository.findById(incoherentEstablishmentId)).thenReturn(Optional.of(incoherentEstablishment));
    }

    @Nested
    @DisplayName("allRegisteredEmployees() tests")
    class AllRegisteredEmployees {

        @Test
        @DisplayName("allRegisteredEmployees(string) throws CompanyNotFoundException with unknown company id")
        void withUnknownCompanyIdOne() {
            assertThrows(CompanyNotFoundException.class, () -> employeeManagementService.allRegisteredEmployees(correctCompanyId + "wrong"));
        }
    }

    @Nested
    @DisplayName("createEmployee() tests")
    class CreateEmployee {

        @Test
        @DisplayName("createEmployee() throws CompanyNotFoundException with unknown company id")
        void withUnknownCompanyId() {
            EmployeeManagementService.EmployeeCreationInformation info = buildCorrectEmployeeInfo();
            assertThrows(CompanyNotFoundException.class,
                         () -> employeeManagementService.createEmployee(correctCompanyId + "wrong", info));
        }

        private EmployeeManagementService.EmployeeCreationInformation buildCorrectEmployeeInfo() {
            return new EmployeeManagementService.EmployeeCreationInformation("guil.rako@hotmail.fr", "Callimard94500$", "Guillaume",
                                                                             "Rakotomalala", null, null, "+33 6 52 44 77 78", true,
                                                                             Lists.newArrayList(), Lists.newArrayList());
        }
    }

    @Nested
    @DisplayName("getSpecificEmployee() tests")
    class GetSpecificEmployee {

        @Test
        @DisplayName("getSpecificEmployee() throws CompanyCoherenceException if the employee id is not coherent with the company id")
        void withIncoherentEmployee() {
            assertThrows(CompanyCoherenceException.class, () -> employeeManagementService.getSpecificEmployee(correctCompanyId,
                                                                                                              incoherentEmployeeId));
        }

        @Test
        @DisplayName("getSpecificEmployee() throws EmployeeNotFound if the employee does not exists")
        void withUnknownEmployee() {
            assertThrows(EmployeeNotFoundException.class, () -> employeeManagementService.getSpecificEmployee(correctCompanyId,
                                                                                                              correctEmployeeId + "wrong"));
        }

        @Test
        @DisplayName("getSpecificEmployee() returns the correct employee with correct company id and employee id")
        void withAllCorrect() {
            Employee employee = employeeManagementService.getSpecificEmployee(correctCompanyId, correctEmployeeId);
            assertThat(employee).isNotNull().isSameAs(correctEmployee);
        }
    }

    @Nested
    @DisplayName("updateEmployeeInformation() tests")
    class UpdateEmployeeInformation {

        @Test
        @DisplayName("updateEmployeeInformation() throws EmployeeNotFoundException with unknown employee id")
        void withUnknownEmployeeId() {
            EmployeeManagementService.EmployeeUpdatedInformation info = buildCorrectEmployeeUpdatedInfo();
            assertThrows(EmployeeNotFoundException.class,
                         () -> employeeManagementService.updateEmployeeInformation(correctCompanyId, correctEmployeeId + "wrong", info));
        }

        @Test
        @DisplayName("updateEmployeeInformation() throws CompanyCoherenceException with unknown employee not in the company")
        void withIncoherentEmployee() {
            EmployeeManagementService.EmployeeUpdatedInformation info = buildCorrectEmployeeUpdatedInfo();
            assertThrows(CompanyCoherenceException.class,
                         () -> employeeManagementService.updateEmployeeInformation(correctCompanyId, incoherentEmployeeId, info));
        }

        private EmployeeManagementService.EmployeeUpdatedInformation buildCorrectEmployeeUpdatedInfo() {
            return new EmployeeManagementService.EmployeeUpdatedInformation("newSuperPassword945$", "FirstName", "LastName", "Salut salut",
                                                                            PersonGender.FEMALE,
                                                                            "+33 6 95 55 88 77",
                                                                            true, Lists.newArrayList());
        }
    }

    @Nested
    @DisplayName("updateEmployeeRoles() tests")
    class UpdateEmployeeRoles {

        @Test
        @DisplayName("updateEmployeeRoles() throws EmployeeNotFoundException with unknown employee id")
        void withUnknownEmployeeId() {
            EmployeeManagementService.EmployeeUpdatedRoles info = buildCorrectEmployeeUpdateRoleInfo();
            assertThrows(EmployeeNotFoundException.class,
                         () -> employeeManagementService.updateEmployeeRoles(correctCompanyId, correctEmployeeId + "wrong", info));
        }

        @Test
        @DisplayName("updateEmployeeRoles() throws CompanyCoherenceException with unknown employee not in the company")
        void withIncoherentEmployee() {
            EmployeeManagementService.EmployeeUpdatedRoles info = buildCorrectEmployeeUpdateRoleInfo();
            assertThrows(CompanyCoherenceException.class,
                         () -> employeeManagementService.updateEmployeeRoles(correctCompanyId, incoherentEmployeeId, info));
        }

        private EmployeeManagementService.EmployeeUpdatedRoles buildCorrectEmployeeUpdateRoleInfo() {
            return new EmployeeManagementService.EmployeeUpdatedRoles(Lists.newArrayList());
        }
    }

    @Nested
    @DisplayName("assignEmployeeToEstablishment() tests")
    class AssignEmployeeToEstablishment {

        @Test
        @DisplayName("assignEmployeeToEstablishment() throws EmployeeNotFoundException with unknown employee id")
        void withUnknownEmployeeId() {
            assertThrows(EmployeeNotFoundException.class,
                         () -> employeeManagementService.assignEmployeeToEstablishment(correctCompanyId, correctEmployeeId + "wrong",
                                                                                       correctEstablishmentId));
        }

        @Test
        @DisplayName("assignEmployeeToEstablishment() throws EstablishmentNotFoundException with unknown employee id")
        void withUnknownEstablishmentId() {
            assertThrows(EstablishmentNotFoundException.class,
                         () -> employeeManagementService.assignEmployeeToEstablishment(correctCompanyId, correctEmployeeId,
                                                                                       correctEstablishmentId + "wrong"));
        }

        @Test
        @DisplayName("assignEmployeeToEstablishment() throws CompanyCoherenceException with incoherent employee")
        void withIncoherentEmployee() {
            assertThrows(CompanyCoherenceException.class,
                         () -> employeeManagementService.assignEmployeeToEstablishment(correctCompanyId, incoherentEmployeeId,
                                                                                       correctEstablishmentId));
        }

        @Test
        @DisplayName("assignEmployeeToEstablishment() throws CompanyCoherenceException with incoherent establishment")
        void withIncoherentEstablishment() {
            assertThrows(CompanyCoherenceException.class,
                         () -> employeeManagementService.assignEmployeeToEstablishment(correctCompanyId, correctEmployeeId,
                                                                                       incoherentEstablishmentId));
        }
    }

    @Nested
    @DisplayName("deAssignEmployeeToEstablishment() tests")
    class DeAssignEmployeeToEstablishment {

        @Test
        @DisplayName("deAssignEmployeeToEstablishment() throws EmployeeNotFoundException with unknown employee id")
        void withUnknownEmployeeId() {
            assertThrows(EmployeeNotFoundException.class,
                         () -> employeeManagementService.deAssignEmployeeToEstablishment(correctCompanyId, correctEmployeeId + "wrong",
                                                                                         correctEstablishmentId));
        }

        @Test
        @DisplayName("deAssignEmployeeToEstablishment() throws EstablishmentNotFoundException with unknown employee id")
        void withUnknownEstablishmentId() {
            assertThrows(EstablishmentNotFoundException.class,
                         () -> employeeManagementService.deAssignEmployeeToEstablishment(correctCompanyId, correctEmployeeId,
                                                                                         correctEstablishmentId + "wrong"));
        }

        @Test
        @DisplayName("deAssignEmployeeToEstablishment() throws CompanyCoherenceException with incoherent employee")
        void withIncoherentEmployee() {
            assertThrows(CompanyCoherenceException.class,
                         () -> employeeManagementService.deAssignEmployeeToEstablishment(correctCompanyId, incoherentEmployeeId,
                                                                                         correctEstablishmentId));
        }

        @Test
        @DisplayName("deAssignEmployeeToEstablishment() throws CompanyCoherenceException with incoherent establishment")
        void withIncoherentEstablishment() {
            assertThrows(CompanyCoherenceException.class,
                         () -> employeeManagementService.deAssignEmployeeToEstablishment(correctCompanyId, correctEmployeeId,
                                                                                         incoherentEstablishmentId));
        }
    }

    @Nested
    @DisplayName("activateEmployee() tests")
    class ActivateEmployee {

        @Test
        @DisplayName("activateEmployee() throws EmployeeNotFoundException with unknown employee id")
        void withUnknownEmployeeId() {
            assertThrows(EmployeeNotFoundException.class,
                         () -> employeeManagementService.activateEmployee(correctCompanyId, correctEmployeeId + "wrong"));
        }

        @Test
        @DisplayName("activateEmployee() throws CompanyCoherenceException with unknown employee id")
        void withIncoherentEmployee() {
            assertThrows(CompanyCoherenceException.class,
                         () -> employeeManagementService.activateEmployee(correctCompanyId, incoherentEmployeeId));
        }
    }

    @Nested
    @DisplayName("deactivateEmployee() tests")
    class DeactivateEmployee {

        @Test
        @DisplayName("deactivateEmployee() throws EmployeeNotFoundException with unknown employee id")
        void withUnknownEmployeeId() {
            assertThrows(EmployeeNotFoundException.class,
                         () -> employeeManagementService.deactivateEmployee(correctCompanyId, correctEmployeeId + "wrong"));
        }

        @Test
        @DisplayName("deactivateEmployee() throws CompanyCoherenceException with unknown employee id")
        void withIncoherentEmployee() {
            assertThrows(CompanyCoherenceException.class,
                         () -> employeeManagementService.deactivateEmployee(correctCompanyId, incoherentEmployeeId));
        }
    }
}

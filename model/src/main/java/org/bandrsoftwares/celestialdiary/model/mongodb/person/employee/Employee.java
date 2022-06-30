package org.bandrsoftwares.celestialdiary.model.mongodb.person.employee;

import com.google.common.collect.Sets;
import lombok.*;
import lombok.extern.slf4j.Slf4j;
import org.bandrsoftwares.celestialdiary.model.mongodb.company.Company;
import org.bandrsoftwares.celestialdiary.model.mongodb.company.CompanySummary;
import org.bandrsoftwares.celestialdiary.model.mongodb.establishment.Establishment;
import org.bandrsoftwares.celestialdiary.model.mongodb.person.PersonGender;
import org.bandrsoftwares.celestialdiary.security.privilege.company.CompanyPrivilege;
import org.bandrsoftwares.celestialdiary.security.privilege.establishment.EstablishmentPrivilege;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.DocumentReference;

import java.time.Instant;
import java.util.List;
import java.util.Optional;
import java.util.Set;

/**
 * A User
 */
@Slf4j
@ToString
@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "Employee")
public class Employee {

    @Id
    private String id;

    private String email;
    private String password;

    private String firstName;
    private String lastName;

    private String comment;

    private PersonGender gender;

    private String phone;

    private Boolean isTechnician;

    private Boolean active;

    private List<EmployeeTag> tags;

    @ToString.Exclude
    @DocumentReference(collection = "Role", lazy = true)
    private List<Role> roles;

    private CompanySummary companySummary;

    private Instant creationDate;

    // Methods.

    public EmployeeSummary summary() {
        return EmployeeSummary.builder()
                .userEmail(email)
                .userFirstName(firstName)
                .userLastName(lastName)
                .employee(this)
                .build();
    }

    public Set<String> allAuthorities() {
        Set<String> authorities = Sets.newHashSet();
        if (roles != null)
            for (Role role : roles) {
                Company roleCompany = role.getCompanySummary().getCompany();
                Establishment roleEstablishment = role.getEstablishment();
                for (Privilege privilege : role.getPrivileges()) {
                    Optional<CompanyPrivilege> opCompanyPrivilege = extractCompanyPrivilege(privilege);
                    Optional<EstablishmentPrivilege> opEstablishmentPrivilege = extractEmployeePrivilege(privilege);

                    if (opCompanyPrivilege.isPresent()) {
                        authorities.add(opCompanyPrivilege.get().getPrivilege(roleCompany.getId()));
                    } else if (opEstablishmentPrivilege.isPresent()) {
                        authorities.add(opEstablishmentPrivilege.get().getPrivilege(roleCompany.getId(), roleEstablishment.getId()));
                    } else {
                        log.error("No convertible privilege {} -> privilege ignored", privilege);
                    }
                }
            }
        return authorities;
    }

    private Optional<CompanyPrivilege> extractCompanyPrivilege(Privilege privilege) {
        try {
            return Optional.of(CompanyPrivilege.valueOf(privilege.getIdentifierName()));
        } catch (IllegalArgumentException e) {
            return Optional.empty();
        }
    }

    private Optional<EstablishmentPrivilege> extractEmployeePrivilege(Privilege privilege) {
        try {
            return Optional.of(EstablishmentPrivilege.valueOf(privilege.getIdentifierName()));
        } catch (IllegalArgumentException e) {
            return Optional.empty();
        }
    }

    // Enum

    public enum EmployeeTag {
        SECRETARY, TECHNICIAN
    }
}

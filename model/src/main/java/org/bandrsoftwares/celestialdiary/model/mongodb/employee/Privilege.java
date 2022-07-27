package org.bandrsoftwares.celestialdiary.model.mongodb.employee;

import lombok.*;
import org.bandrsoftwares.celestialdiary.security.privilege.PrivilegeEnum;
import org.bandrsoftwares.celestialdiary.security.privilege.company.CompanyManagementPrivilege;
import org.bandrsoftwares.celestialdiary.security.privilege.establishment.EstablishmentPrivilege;

import java.util.Optional;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Privilege {

    private String identifierName;
    private String name;
    private String description;

    public static Privilege of(PrivilegeEnum privilegeEnum) {
        return Privilege.builder()
                .identifierName(privilegeEnum.enumName())
                .name(privilegeEnum.getPrivilegeName())
                .description(privilegeEnum.getPrivilegeDescription())
                .build();
    }

    public static Optional<CompanyManagementPrivilege> extractCompanyPrivilege(Privilege privilege) {
        return extractCompanyPrivilege(privilege.getIdentifierName());
    }

    public static Optional<CompanyManagementPrivilege> extractCompanyPrivilege(String identifierName) {
        try {
            return Optional.of(CompanyManagementPrivilege.valueOf(identifierName));
        } catch (IllegalArgumentException e) {
            return Optional.empty();
        }
    }

    public static Optional<EstablishmentPrivilege> extractEstablishmentPrivilege(Privilege privilege) {
        return extractEstablishmentPrivilege(privilege.getIdentifierName());
    }

    public static Optional<EstablishmentPrivilege> extractEstablishmentPrivilege(String identifierName) {
        try {
            return Optional.of(EstablishmentPrivilege.valueOf(identifierName));
        } catch (IllegalArgumentException e) {
            return Optional.empty();
        }
    }

    public static Privilege extractPrivilegeFromIdentifier(String identifierName) {
        Optional<CompanyManagementPrivilege> opCMP = extractCompanyPrivilege(identifierName);
        Optional<EstablishmentPrivilege> opEMP = extractEstablishmentPrivilege(identifierName);

        if (opCMP.isPresent()) {
            return Privilege.of(opCMP.get());
        } else return opEMP.map(Privilege::of).orElse(null);
    }
}

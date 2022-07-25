package org.bandrsoftwares.celestialdiary.model.mongodb.employee;

import lombok.*;
import org.bandrsoftwares.celestialdiary.security.privilege.PrivilegeEnum;

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
}

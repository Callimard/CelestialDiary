package org.bandrsoftwares.celestialdiary.aop.employee;

import java.lang.annotation.*;

@Inherited
@Target(ElementType.PARAMETER)
@Retention(RetentionPolicy.RUNTIME)
public @interface EmployeeId {
}

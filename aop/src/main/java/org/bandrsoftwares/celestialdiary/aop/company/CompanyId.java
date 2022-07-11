package org.bandrsoftwares.celestialdiary.aop.company;

import java.lang.annotation.*;

@Inherited
@Target(ElementType.PARAMETER)
@Retention(RetentionPolicy.RUNTIME)
public @interface CompanyId {
}

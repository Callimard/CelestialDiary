package org.bandrsoftwares.celestialdiary.aop.company;

import java.lang.annotation.*;

@Inherited
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface CheckCompanyCoherence {
}

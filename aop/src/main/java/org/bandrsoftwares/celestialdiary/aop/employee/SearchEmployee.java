package org.bandrsoftwares.celestialdiary.aop.employee;

import java.lang.annotation.*;

@Inherited
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface SearchEmployee {
}

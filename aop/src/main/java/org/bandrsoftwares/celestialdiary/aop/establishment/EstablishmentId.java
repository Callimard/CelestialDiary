package org.bandrsoftwares.celestialdiary.aop.establishment;

import java.lang.annotation.*;

@Inherited
@Target(ElementType.PARAMETER)
@Retention(RetentionPolicy.RUNTIME)
public @interface EstablishmentId {
}

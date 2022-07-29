package org.bandrsoftwares.celestialdiary.aop.equipment;

import java.lang.annotation.*;

@Inherited
@Target(ElementType.PARAMETER)
@Retention(RetentionPolicy.RUNTIME)
public @interface EquipmentId {
}

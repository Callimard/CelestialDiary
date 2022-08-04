package org.bandrsoftwares.celestialdiary.aop.client;


import java.lang.annotation.*;

@Inherited
@Target(ElementType.PARAMETER)
@Retention(RetentionPolicy.RUNTIME)
public @interface ClientId {
}

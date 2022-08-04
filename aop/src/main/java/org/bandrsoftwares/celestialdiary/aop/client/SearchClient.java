package org.bandrsoftwares.celestialdiary.aop.client;

import java.lang.annotation.*;

@Inherited
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface SearchClient {
}

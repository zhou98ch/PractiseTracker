package com.example.server.annotation;

import enumeration.OperationType;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * annotation, used to indicate that a method needs to perform common field filling processing
 */
@Target(ElementType.METHOD)  //specify that the annotation can only be added to methods
@Retention(RetentionPolicy.RUNTIME)

public @interface AutoFill {
    //designate the db operations: update insert
    OperationType value();
}
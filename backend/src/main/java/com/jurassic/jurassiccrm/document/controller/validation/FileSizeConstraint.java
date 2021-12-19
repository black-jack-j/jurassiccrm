package com.jurassic.jurassiccrm.document.controller.validation;

import org.springframework.util.unit.DataUnit;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = FileSizeConstraintValidator.class)
@Target({ElementType.METHOD, ElementType.FIELD, ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
public @interface FileSizeConstraint {
    String message() default "File should be specified and be not empty";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

    long minSize() default 1;

    long maxSize() default 1024;

    DataUnit unit() default DataUnit.KILOBYTES;
}

package com.jurassic.jurassiccrm.validation.existence;

import org.springframework.data.repository.CrudRepository;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = {ExistenceValidator.class, ExistenceValidatorEntity.class})
public @interface NullOrExists {

    String message() default "must be null or exist";
    Class<? extends CrudRepository<?, Long>> repository();
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default { };

}

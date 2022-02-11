package com.jurassic.jurassiccrm.validation.existence;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.data.repository.CrudRepository;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class ExistenceValidator implements ConstraintValidator<NullOrExists, Long> {

    private final ApplicationContext context;
    private CrudRepository<?, Long> provider;

    @Autowired
    public ExistenceValidator(ApplicationContext context) {
        this.context = context;
    }

    @Override
    public void initialize(NullOrExists constraintAnnotation) {
        provider = context.getBean(constraintAnnotation.repository());
    }

    @Override
    public boolean isValid(Long value, ConstraintValidatorContext context) {
        return value == null || provider.existsById(value);
    }

}

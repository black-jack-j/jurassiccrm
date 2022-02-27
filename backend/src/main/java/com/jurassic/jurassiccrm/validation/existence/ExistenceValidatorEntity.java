package com.jurassic.jurassiccrm.validation.existence;

import com.jurassic.jurassiccrm.common.dto.SimpleEntityOutputTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.data.repository.CrudRepository;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class ExistenceValidatorEntity implements ConstraintValidator<NullOrExists, SimpleEntityOutputTO> {

    private final ApplicationContext context;
    private CrudRepository<?, Long> provider;

    @Autowired
    public ExistenceValidatorEntity(ApplicationContext context) {
        this.context = context;
    }

    @Override
    public void initialize(NullOrExists constraintAnnotation) {
        provider = context.getBean(constraintAnnotation.repository());
    }

    @Override
    public boolean isValid(SimpleEntityOutputTO value, ConstraintValidatorContext context) {
        return value == null || value.getId() == null || provider.existsById(value.getId());
    }
}

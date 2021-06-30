package com.jurassic.jurassiccrm.document.controller.validation;

import org.springframework.util.unit.DataSize;
import org.springframework.util.unit.DataUnit;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class FileSizeConstraintValidator implements ConstraintValidator<FileSizeConstraint, MultipartFile> {

    private long min;
    private long max;
    private DataUnit dataUnit;

    @Override
    public void initialize(FileSizeConstraint constraintAnnotation) {
        min = constraintAnnotation.minSize();
        max = constraintAnnotation.maxSize();
        dataUnit = constraintAnnotation.unit();
    }

    @Override
    public boolean isValid(MultipartFile multipartFile, ConstraintValidatorContext constraintValidatorContext) {
        return multipartFile.getSize() >= DataSize.of(min, dataUnit).toBytes() &&
                multipartFile.getSize() <= DataSize.of(max, dataUnit).toBytes();
    }
}

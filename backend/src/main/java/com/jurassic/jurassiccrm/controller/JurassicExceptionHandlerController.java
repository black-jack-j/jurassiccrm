package com.jurassic.jurassiccrm.controller;

import com.jurassic.jurassiccrm.validation.ValidationResponseTO;
import com.jurassic.jurassiccrm.validation.ViolationTO;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.NoHandlerFoundException;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import java.util.List;
import java.util.stream.Collectors;

@ControllerAdvice
public class JurassicExceptionHandlerController {

    @ExceptionHandler(NoHandlerFoundException.class)
    public String handle(Exception exception) {
        return "redirect:/";
    }

    @ExceptionHandler(ConstraintViolationException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    ValidationResponseTO onConstraintsViolationException(ConstraintViolationException e) {
        ValidationResponseTO validationResponseTO = new ValidationResponseTO();
        List<ViolationTO> violations = e.getConstraintViolations().stream()
                .map(JurassicExceptionHandlerController::getConstraintViolationTO)
                .collect(Collectors.toList());

        validationResponseTO.setViolations(violations);
        return validationResponseTO;
    }

    private static ViolationTO getConstraintViolationTO(ConstraintViolation violation) {
        return new ViolationTO(violation.getPropertyPath().toString(), violation.getMessage());
    }

}

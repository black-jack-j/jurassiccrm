package com.jurassic.jurassiccrm.controller;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.NoHandlerFoundException;

@ControllerAdvice
public class NotFoundController {

    @ExceptionHandler(NoHandlerFoundException.class)
    public String handle(Exception exception) {
        return "redirect:/";
    }

}

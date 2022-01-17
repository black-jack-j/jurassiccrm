package com.jurassic.jurassiccrm.controller;

import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class JurassicErrorController implements ErrorController {

    @RequestMapping("/error")
    public String handleError() {
        return "redirect:/";
    }

    @Override
    public String getErrorPath() {
        return "/error";
    }
}

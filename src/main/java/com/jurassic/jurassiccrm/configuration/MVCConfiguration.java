package com.jurassic.jurassiccrm.configuration;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class MVCConfiguration implements WebMvcConfigurer {

    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/login").setViewName("login");
        registry.addViewController("/document").setViewName("document");
        registry.addViewController("/security").setViewName("security");
        registry.addViewController("/task").setViewName("task");
        registry.addViewController("/admin").setViewName("admin");
    }
}

package com.jurassic.jurassiccrm.configuration;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class MVCConfiguration implements WebMvcConfigurer {

    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/login").setViewName("/login");
        registry.addViewController("/document").setViewName("/document/index");
        registry.addViewController("/security").setViewName("/security");
        registry.addViewController("/task/index").setViewName("/task/index");
        registry.addViewController("/admin").setViewName("/admin");
        registry.addViewController("/document/upload").setViewName("/document/upload");
        registry.addViewController("/document/view").setViewName("/document/view");
        registry.addViewController("/task/create-task").setViewName("/task/create");
        registry.addViewController("/login").setViewName("login");
        registry.addViewController("/document").setViewName("document");
        registry.addViewController("/security").setViewName("security");
        registry.addViewController("/task").setViewName("task");
        registry.addViewController("/admin").setViewName("admin");
        registry.addViewController("/wiki/home").setViewName("/wiki/home");
        registry.addViewController("/wiki/page").setViewName("/wiki/page");
    }
}

package com.jurassic.jurassiccrm.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.thymeleaf.spring5.templateresolver.SpringResourceTemplateResolver;
import org.thymeleaf.spring5.view.ThymeleafViewResolver;


@Configuration
public class MVCConfiguration implements WebMvcConfigurer {

    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/login").setViewName("login");
        registry.addViewController("/wiki/home").setViewName("/wiki/home");
        registry.addViewController("/wiki/page").setViewName("/wiki/page");
        registry.addViewController("/wiki/create").setViewName("/wiki/create");
        registry.addViewController("/wiki/edit").setViewName("/wiki/edit");
    }

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry
                .addResourceHandler("/webjars/**", "/templates/**", "/static/**")
                .addResourceLocations("/webjars/", "webjars/static", "/templates", "/static")
                .resourceChain(false);
    }

}

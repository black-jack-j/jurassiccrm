package com.jurassic.jurassiccrm.configuration;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;


@Configuration
public class MVCConfiguration implements WebMvcConfigurer {

    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/login").setViewName("login");
        registry.addViewController("/wiki/home").setViewName("/wiki/home");
        registry.addViewController("/wiki/page").setViewName("/wiki/page");
        registry.addViewController("/wiki/admin").setViewName("/wiki/admin");
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

    @Bean
    public OpenAPI springShopOpenAPI() {
        return new OpenAPI()
                .info(new Info().title("Jurassic CRM API")
                        .description("Jurassic CRM")
                        .version("v0.0.1")
                        .license(new License().name("Apache 2.0").url("http://springdoc.org")));
    }

}

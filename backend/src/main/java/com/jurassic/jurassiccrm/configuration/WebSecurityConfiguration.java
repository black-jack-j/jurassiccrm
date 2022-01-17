package com.jurassic.jurassiccrm.configuration;

import com.jurassic.jurassiccrm.accesscontroll.JurassicUserDetailsService;
import com.jurassic.jurassiccrm.accesscontroll.entity.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.hierarchicalroles.RoleHierarchy;
import org.springframework.security.access.hierarchicalroles.RoleHierarchyAuthoritiesMapper;
import org.springframework.security.access.hierarchicalroles.RoleHierarchyImpl;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.HttpStatusEntryPoint;

@Configuration
@EnableWebSecurity
public class WebSecurityConfiguration extends WebSecurityConfigurerAdapter {

    private final JurassicUserDetailsService jurassicUserDetailsService;

    private final String[] taskReaderRoles = {
            Role.ADMIN.name(),
            Role.TASK_READER.name(),
            Role.INCUBATION_TASK_READER.name(),
            Role.AVIARY_BUILDING_TASK_READER.name(),
            Role.RESEARCH_TASK_READER.name()};

    private final String[] documentReaderRoles = {
            Role.ADMIN.name(),
            Role.DOCUMENT_READER.name(),
            Role.DINOSAUR_PASSPORT_READER.name(),
            Role.AVIARY_PASSPORT_READER.name(),
            Role.RESEARCH_DATA_READER.name(),
            Role.THEME_ZONE_PROJECT_READER.name(),
            Role.TECHNOLOGICAL_MAP_READER.name()};

    private final String[] documentWriterRoles = {
            Role.ADMIN.name(),
            Role.DOCUMENT_WRITER.name(),
            Role.DINOSAUR_PASSPORT_WRITER.name(),
            Role.AVIARY_PASSPORT_WRITER.name(),
            Role.RESEARCH_DATA_WRITER.name(),
            Role.THEME_ZONE_PROJECT_WRITER.name(),
            Role.TECHNOLOGICAL_MAP_WRITER.name()};

    @Autowired
    public WebSecurityConfiguration(JurassicUserDetailsService jurassicUserDetailsService) {
        this.jurassicUserDetailsService = jurassicUserDetailsService;
    }



    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                    .antMatchers("/wiki/**").permitAll()
                    .antMatchers("/img/**", "styles/**", "/js/**", "/webjars/**", "/static/**").permitAll()
                    .mvcMatchers("/login").permitAll()
                    .anyRequest().authenticated()
                    .and()
                .formLogin()
                    .loginPage("/login").permitAll()
                    .defaultSuccessUrl("/")
                .and()
                    .logout().permitAll()
                .and()
                    .httpBasic()
                .and()
                    .exceptionHandling()
                    .authenticationEntryPoint(new HttpStatusEntryPoint(HttpStatus.UNAUTHORIZED));
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(jurassicUserDetailsService);
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public RoleHierarchy roleHierarchy() {
        RoleHierarchyImpl roleHierarchy = new RoleHierarchyImpl();
        String hierarchy = Role.DOCUMENT_READER + " > " + Role.DINOSAUR_PASSPORT_READER.name() + "\n" +
                Role.DOCUMENT_READER + " > " + Role.AVIARY_PASSPORT_READER.name() + "\n" +
                Role.DOCUMENT_READER + " > " + Role.RESEARCH_DATA_READER.name() + "\n" +
                Role.DOCUMENT_READER + " > " + Role.TECHNOLOGICAL_MAP_READER.name() + "\n" +
                Role.DOCUMENT_READER + " > " + Role.THEME_ZONE_PROJECT_READER.name() + "\n" +
                Role.DOCUMENT_WRITER + " > " + Role.DINOSAUR_PASSPORT_WRITER.name() + "\n" +
                Role.DOCUMENT_WRITER + " > " + Role.AVIARY_PASSPORT_WRITER.name() + "\n" +
                Role.DOCUMENT_WRITER + " > " + Role.RESEARCH_DATA_WRITER.name() + "\n" +
                Role.DOCUMENT_WRITER + " > " + Role.TECHNOLOGICAL_MAP_WRITER.name() + "\n" +
                Role.DOCUMENT_WRITER + " > " + Role.THEME_ZONE_PROJECT_WRITER.name() + "\n" +
                Role.TASK_READER + " > " + Role.AVIARY_BUILDING_TASK_READER.name() + "\n" +
                Role.TASK_READER + " > " + Role.INCUBATION_TASK_READER.name() + "\n" +
                Role.TASK_READER + " > " + Role.RESEARCH_TASK_READER.name() + "\n" +
                Role.TASK_WRITER + " > " + Role.AVIARY_BUILDING_TASK_WRITER.name() + "\n" +
                Role.TASK_WRITER + " > " + Role.INCUBATION_TASK_WRITER.name() + "\n" +
                Role.TASK_WRITER + " > " + Role.RESEARCH_TASK_WRITER.name() + "\n";
        roleHierarchy.setHierarchy(hierarchy);
        return roleHierarchy;
    }

    @Bean
    public RoleHierarchyAuthoritiesMapper roleHierarchyAuthoritiesMapper(RoleHierarchy roleHierarchy) {
        return new RoleHierarchyAuthoritiesMapper(roleHierarchy);
    }
}

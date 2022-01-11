package com.jurassic.jurassiccrm.configuration;

import com.jurassic.jurassiccrm.accesscontroll.JurassicUserDetailsService;
import com.jurassic.jurassiccrm.accesscontroll.entity.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.access.hierarchicalroles.RoleHierarchy;
import org.springframework.security.access.hierarchicalroles.RoleHierarchyAuthoritiesMapper;
import org.springframework.security.access.hierarchicalroles.RoleHierarchyImpl;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

import java.util.Arrays;
import java.util.List;

@Configuration
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private AuthenticationSuccessHandler authenticationSuccessHandler;

    @Autowired
    private JurassicUserDetailsService userDetailsService;

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

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers("/wiki/home").permitAll()
                .antMatchers("/wiki/page*").permitAll()
                .antMatchers("/wiki/getAllPages").permitAll()
                .antMatchers("/wiki/getAllTitles").permitAll()
                .antMatchers("/wiki/findByTitle*").permitAll()
                .antMatchers("/wiki/deleteByTitle*").permitAll() // исправить
                .antMatchers("/wiki/updateWikiPage*").permitAll() // исправить
                .antMatchers("/wiki/createWikiPage*").permitAll() // исправить
                .antMatchers("/wiki/admin").hasRole(Role.ADMIN.name())
                .antMatchers("/img/**", "styles/**", "/js/**", "/wiki/**", "/webjars/**", "/static/**").permitAll()
                .antMatchers("/document/").hasAnyRole(Role.ADMIN.name(), Role.DOCUMENT_READER.name())
                .antMatchers("/document/index").hasAnyRole(documentReaderRoles)
                .antMatchers("/document/view").hasAnyRole(documentReaderRoles)
                .antMatchers("/document/upload").hasAnyRole(documentWriterRoles)
                .antMatchers("/security/").hasAnyRole(Role.ADMIN.name(), Role.SECURITY_READER.name())
                .antMatchers("/security/**").hasAnyRole(Role.ADMIN.name(), Role.SECURITY_WRITER.name())
                .antMatchers("/task/**").hasAnyRole(taskReaderRoles)
                .antMatchers("/group/").hasAnyRole(Role.ADMIN.name(), Role.SECURITY_WRITER.name())
                .antMatchers("/group/**").hasAnyRole(Role.ADMIN.name(), Role.SECURITY_WRITER.name())
                .antMatchers("/admin/**").hasRole(Role.ADMIN.name())
                .anyRequest().authenticated()
                .and()
                .formLogin()
                .loginPage("/login").permitAll()
                .successHandler(authenticationSuccessHandler)
                .and()
                .logout().permitAll();
        http.csrf().disable();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService);
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

    /*@Bean
    public DefaultWebSecurityExpressionHandler webSecurityExpressionHandler(RoleHierarchy roleHierarchy) {
        DefaultWebSecurityExpressionHandler expressionHandler = new DefaultWebSecurityExpressionHandler();
        expressionHandler.setRoleHierarchy(roleHierarchy);
        return expressionHandler;
    }*/
}

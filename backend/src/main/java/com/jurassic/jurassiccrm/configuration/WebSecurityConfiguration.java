package com.jurassic.jurassiccrm.configuration;

import com.jurassic.jurassiccrm.accesscontroll.JurassicUserDetailsService;
import com.jurassic.jurassiccrm.accesscontroll.model.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;
import org.springframework.http.HttpMethod;
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
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.authentication.DelegatingAuthenticationEntryPoint;
import org.springframework.security.web.authentication.HttpStatusEntryPoint;
import org.springframework.security.web.authentication.LoginUrlAuthenticationEntryPoint;
import org.springframework.security.web.util.matcher.RegexRequestMatcher;
import org.springframework.security.web.util.matcher.RequestMatcher;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.LinkedHashMap;

@Configuration
@EnableWebSecurity
public class WebSecurityConfiguration extends WebSecurityConfigurerAdapter implements WebMvcConfigurer {

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
    public WebSecurityConfiguration(@Lazy JurassicUserDetailsService jurassicUserDetailsService) {
        this.jurassicUserDetailsService = jurassicUserDetailsService;
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                    .antMatchers(HttpMethod.GET, "/wiki/**", "/wiki", "/api/wiki/**").permitAll()
                    .antMatchers("/static/**", "/img/**", "styles/**", "/js/**", "/webjars/**", "/*.js", "/actuator/**", "/v3/api-docs").permitAll()
                    .mvcMatchers("/login").permitAll()
                    .mvcMatchers("/wiki/page").permitAll()
                    .mvcMatchers(HttpMethod.DELETE,"/api/wiki/*").permitAll() // TODO: исправить
                    .mvcMatchers("/wiki/admin").permitAll()
                    .mvcMatchers("/wiki/create").permitAll() //  TODO: исправить
                    .mvcMatchers("/wiki/createWiki").permitAll() //  TODO: исправить
                    .mvcMatchers("/wiki/edit").permitAll() //  TODO: исправить
                    .mvcMatchers("/wiki/editWiki1").permitAll() //  TODO: исправить
                    .mvcMatchers("/wiki/editWiki2").permitAll() //  TODO: исправить
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
                    .csrf().disable() //should be removed and csrf token used from react side (if we will have time)
                    .exceptionHandling()
                    .authenticationEntryPoint(getAuthenticationEntryPoint());
    }

    private AuthenticationEntryPoint getAuthenticationEntryPoint() {
        LinkedHashMap<RequestMatcher, AuthenticationEntryPoint> entryPoints = new LinkedHashMap<>();
        entryPoints.put(new RegexRequestMatcher("^/api/.*", null), new HttpStatusEntryPoint(HttpStatus.UNAUTHORIZED));

        DelegatingAuthenticationEntryPoint entryPoint = new DelegatingAuthenticationEntryPoint(entryPoints);
        entryPoint.setDefaultEntryPoint(new LoginUrlAuthenticationEntryPoint("/login"));

        return entryPoint;
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

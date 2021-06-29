package com.jurassic.jurassiccrm.configuration;

import com.jurassic.jurassiccrm.accesscontroll.JurassicUserDetailsService;
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

@Configuration
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private AuthenticationSuccessHandler authenticationSuccessHandler;

    @Autowired
    private JurassicUserDetailsService userDetailsService;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers("/wiki/**").permitAll()
                .antMatchers("/img/**").permitAll()
                .antMatchers("/styles/**").permitAll()
                .antMatchers("/document/").hasAnyRole("ADMIN", "DOCUMENT_READER")
                .antMatchers("/document/index").hasAnyRole("ADMIN", "DOCUMENT_READER")
                .antMatchers("/document/view").hasAnyRole("ADMIN", "DOCUMENT_READER")
                .antMatchers("/document/upload").hasAnyRole("ADMIN", "DOCUMENT_WRITER")
                .antMatchers("/security/").hasAnyRole("ADMIN", "SECURITY_READER")
                .antMatchers("/security/**").hasAnyRole("ADMIN", "SECURITY_WRITER")
                .antMatchers("/task/").hasAnyRole("ADMIN", "TASK_READER")
                .antMatchers("/task/**").hasAnyRole("ADMIN", "TASK_READER")
                .antMatchers("/group/").hasAnyRole("ADMIN", "GROUP_EDITOR")
                .antMatchers("/group/**").hasAnyRole("ADMIN", "GROUP_EDITOR")
                .antMatchers("/admin/**").hasRole("ADMIN")
                .antMatchers("/h2-console/**").permitAll()
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
        String hierarchy = "DOCUMENT_ADMIN > DOCUMENT_WRITER \n"+
                            "DOCUMENT_WRITER > DOCUMENT_READER\n" +
                            "TASK_ADMIN > TASK_WRITER \n" +
                            "TASK_WRITER > TASK_READER\n";
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

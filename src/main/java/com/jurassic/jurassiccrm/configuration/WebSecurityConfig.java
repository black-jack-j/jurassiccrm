package com.jurassic.jurassiccrm.configuration;

import com.jurassic.jurassiccrm.login.LoginSuccessHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
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

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers("/wiki/**").permitAll()
                .antMatchers("/document/**").hasRole("DOCUMENT_MANAGER")
                .antMatchers("/security/**").hasRole("SECURITY_MANAGER")
                .antMatchers("/task/**").hasRole("TASK_MANAGER")
                .antMatchers("/admin/**").hasRole("ADMIN")
                .anyRequest().authenticated()
                .and()
                .formLogin()
                    .loginPage("/login").permitAll()
                    .successHandler(authenticationSuccessHandler)
                .and()
                .logout().permitAll();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth, PasswordEncoder passwordEncoder) throws Exception {
        auth.inMemoryAuthentication()
                .withUser("admin").password(passwordEncoder.encode("admin")).roles("ADMIN");
        auth.inMemoryAuthentication()
                .withUser("test-sec").password(passwordEncoder.encode("test")).roles("SECURITY_MANAGER");
        auth.inMemoryAuthentication()
                .withUser("test-doc").password(passwordEncoder.encode("test")).roles("DOCUMENT_MANAGER");
        auth.inMemoryAuthentication()
                .withUser("test-task").password(passwordEncoder.encode("test")).roles("TASK_MANAGER");
    }
}

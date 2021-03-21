package com.todlist.todlist.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(HttpSecurity httpSecurity) throws Exception {
        httpSecurity
                .csrf().disable() //
                .cors().disable() //
                .authorizeRequests() // localhost:8080/hello | localhost:8080/bye
                .antMatchers("/") // verifica daca routa apelata contine /
                .permitAll(); // daca contine /, permite request-urile fara credentiale
    }
}

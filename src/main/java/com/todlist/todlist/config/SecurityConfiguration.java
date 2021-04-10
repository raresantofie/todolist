package com.todlist.todlist.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

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

    // dezactivam cors pentru orice path, http method si origin
    // de exemplu origin-ul aplicatiei de front end este localhost:4200
    @Bean
    public WebMvcConfigurer corsConfigurer() {
        return new WebMvcConfigurer() {
            @Override
            public void addCorsMappings(CorsRegistry corsRegistry) {
                corsRegistry
                        .addMapping("/**")
                        .allowedMethods("*")
                        .allowedOrigins("*");
            }
        };
    }
}

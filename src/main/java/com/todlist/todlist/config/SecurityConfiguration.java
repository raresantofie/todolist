package com.todlist.todlist.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

    private UserAuthenticationService userAuthenticationService;

    @Autowired
    public SecurityConfiguration(UserAuthenticationService userAuthenticationService) {
        this.userAuthenticationService = userAuthenticationService;
    }

    @Override
    protected void configure(AuthenticationManagerBuilder authenticationManagerBuilder) throws Exception {
        authenticationManagerBuilder
                .userDetailsService(userAuthenticationService)
                .passwordEncoder(new BCryptPasswordEncoder());
    }

    @Override
    protected void configure(HttpSecurity httpSecurity) throws Exception {
        httpSecurity
                .csrf().disable() //
                .cors().disable() //
                .authorizeRequests() // localhost:8080/hello | localhost:8080/bye
                .antMatchers("/roles").permitAll()
                .antMatchers("/roles/**").permitAll()
                .antMatchers("/register").permitAll()
                .antMatchers("/test/public").permitAll()
                .antMatchers("/test/private").authenticated()
                .antMatchers("/test/manager").hasAuthority("MANAGER")
                .antMatchers("/test/developer").hasAuthority("DEVELOPER")
                .anyRequest()
                .authenticated()
                .and()
                .httpBasic();
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

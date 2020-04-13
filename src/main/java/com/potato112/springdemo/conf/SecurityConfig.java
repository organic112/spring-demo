package com.potato112.springdemo.conf;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;


/**
 * This protects endpoints with basic authentication and sets up a user to test with
 */
//@Configuration
//@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

/*    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder authManagerBuilder) throws Exception {

        authManagerBuilder
                .inMemoryAuthentication()
                .withUser("admin")
                .password("{noop}test_pass_01") // password storage format, for plain text, add {noop}
                .roles("ADMIN");
    }

    @Override
    protected void configure(HttpSecurity httpSecurity) throws Exception {
        httpSecurity
                .httpBasic()
                .and().authorizeRequests()
                .antMatchers("/testcontroller")
                .hasRole("ADMIN")
                .anyRequest()
                .authenticated();
    }*/
}

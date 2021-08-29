/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.capstone.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

/**
 *
 * @author fionn
 */

/**
 * Security class that deals with the priviledges that each level of account has
 */
@Configuration
@EnableWebSecurity
public class SecurityConfig  extends WebSecurityConfigurerAdapter {

    /**
     * Method for setting the username and password for both the admin and user account
     *
     * Admin has higher level permissions as they can do everything a user account can do
     * and more.
     * @param auth
     * @throws Exception
     */
    @Autowired
    public void configureGlobalInMemory(AuthenticationManagerBuilder auth) throws Exception {
        auth
                .inMemoryAuthentication()
                    .withUser("user").password("{noop}userPassword").roles("USER")
                    .and()
                    .withUser("admin").password("{noop}adminPassword").roles("ADMIN", "USER");
    }
    
    @Override 
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable();

        http
                .authorizeRequests()
                    .antMatchers("/addBlog.html").hasRole("USER")
                    .antMatchers("/", "/index.html", "/staticPage.html").permitAll()
                .and()
                .formLogin()
//                    .loginPage("/logon.html")
                    .failureUrl("/logon.html")
                    .permitAll()
                .and()
                .logout()
                    .logoutSuccessUrl("/")
                    .permitAll();
    }
}

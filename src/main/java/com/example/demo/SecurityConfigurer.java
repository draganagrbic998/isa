package com.example.demo;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfigurer extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.formLogin().disable().httpBasic().disable()
             .rememberMe().and().csrf().disable();
        http.headers().frameOptions().disable();
        
		http.authorizeRequests().antMatchers("/**").permitAll().antMatchers("/h2/**").permitAll().antMatchers("/registracija").permitAll()
		.anyRequest().authenticated();


    }
}
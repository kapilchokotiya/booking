package com.booking.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
//This annotation indicates that the class is a configuration
// class and contains bean definitions and other configuration settings.
@Configuration
//This annotation is used to enable Spring Security's web security support.
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

//This annotation is used to define a bean (in this case, a PasswordEncoder)
// that will be managed by the Spring container.
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable()
                .authorizeRequests()
                .antMatchers("/api/users/**").permitAll()
                .antMatchers("/api/BusOperator/create").permitAll()
                .antMatchers("/api/BusOperator/buses").permitAll()
                .antMatchers("/api/BusOperator/schedules").permitAll()
                .antMatchers("/api/BusOperator/routes").permitAll()
                .anyRequest().authenticated()
                .and()
                .formLogin().disable()
                .httpBasic().disable();
    }
}
package com.eriks.orderservice.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;

/**
 * The Class ResourceServerConfiguration.
 * 
 * @author Yogesh Paimode
 */
@Configuration
@EnableResourceServer
public class ResourceServerConfiguration extends ResourceServerConfigurerAdapter {

    /**
     * Configure.
     *
     * @param http the http
     * @throws Exception the exception
     */
    @Override
    public void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers("/oauth/token", "/oauth/authorize**")
                .permitAll()
                .antMatchers("/api/orderservice/orders/**", "/api/orderservice/getAllOrders").access("hasRole('USER')");
    }

}

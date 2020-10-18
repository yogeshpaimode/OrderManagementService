package com.eriks.orderservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;


/**
 * The Class OrderserviceApplication.
 * 
 * @author Yogesh Paimode
 */
@SpringBootApplication
@EnableWebSecurity
public class OrderserviceApplication {

    /**
     * The main method.
     *
     * @param args the arguments
     */
    public static void main(String[] args) {
        SpringApplication.run(OrderserviceApplication.class, args);
    }
}

package com.eriks.orderservice.config;

import static springfox.documentation.builders.PathSelectors.regex;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.service.Tag;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * The Class SwaggerConfiguration.
 * 
 * @author Yogesh Paimode
 */
@EnableSwagger2
@Configuration
public class SwaggerConfiguration {

    /** The Constant ORDER_SERVICE_TAG. */
    public static final String ORDER_SERVICE_TAG = "Order Resource";

    /**
     * Product api.
     *
     * @return the docket
     */
    @Bean
    public Docket productApi() {
        return new Docket(DocumentationType.SWAGGER_2).select()
                .apis(RequestHandlerSelectors.basePackage("com.eriks.orderservice"))
                .paths(regex("/api/orderservice.*")).build()
                .tags(new Tag(ORDER_SERVICE_TAG, "Order Management Service Details")).apiInfo(apiInfo());
    }

    /**
     * Api info.
     *
     * @return the api info
     */
    private ApiInfo apiInfo() {
        return new ApiInfoBuilder().title("Order Service Application").version("1.0.0")
                .contact(new Contact("Yogesh", "", "paimodeyogesh001@gmail.com")).build();
    }
}

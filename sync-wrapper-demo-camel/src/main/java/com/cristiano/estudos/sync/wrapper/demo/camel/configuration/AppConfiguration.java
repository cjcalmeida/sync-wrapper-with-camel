package com.cristiano.estudos.sync.wrapper.demo.camel.configuration;

import org.apache.camel.component.jms.JmsComponent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.RestController;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

import javax.jms.ConnectionFactory;

@Configuration
public class AppConfiguration {

    @Bean
    @Autowired
    public JmsComponent jmsComponent(ConnectionFactory connectionFactory){
        return JmsComponent.jmsComponent(connectionFactory);
    }

    @Bean
    public Docket swaggerApi(){
        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                .apis(RequestHandlerSelectors.withClassAnnotation(RestController.class))
                .paths(PathSelectors.any())
                .build()
                .apiInfo(swaggerInfo());
    }

    private ApiInfo swaggerInfo() {
        return new ApiInfoBuilder()
                .title("Person Service")
                .description("Sample service to validate sync wrapper with Camel")
                .version("1.0")
                .build();
    }

}

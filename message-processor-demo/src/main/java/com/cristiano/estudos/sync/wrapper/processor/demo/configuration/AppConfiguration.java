package com.cristiano.estudos.sync.wrapper.processor.demo.configuration;

import com.cristiano.estudos.sync.wrapper.processor.demo.domain.PersonRepository;
import com.cristiano.estudos.sync.wrapper.processor.demo.domain.PersonService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jms.annotation.JmsListenerConfigurer;
import org.springframework.jms.config.JmsListenerEndpointRegistrar;
import org.springframework.messaging.converter.MappingJackson2MessageConverter;
import org.springframework.messaging.converter.MessageConverter;
import org.springframework.messaging.handler.annotation.support.DefaultMessageHandlerMethodFactory;

@Configuration
public class AppConfiguration implements JmsListenerConfigurer {

    @Bean
    public ObjectMapper objectMapper(){
        return new ObjectMapper();
    }

    @Bean
    public MessageConverter messageConverter(){
        return new MappingJackson2MessageConverter();
    }

    @Bean
    public DefaultMessageHandlerMethodFactory defaultMessageHandlerMethodFactory(){
        DefaultMessageHandlerMethodFactory factory = new DefaultMessageHandlerMethodFactory();
        factory.setMessageConverter(messageConverter());
        return factory;
    }

    @Override
    public void configureJmsListeners(JmsListenerEndpointRegistrar jmsListenerEndpointRegistrar) {
        jmsListenerEndpointRegistrar.setMessageHandlerMethodFactory(defaultMessageHandlerMethodFactory());
    }

    @Bean
    @Autowired
    public PersonService personService(PersonRepository repository){
        return new PersonService(repository);
    }
}

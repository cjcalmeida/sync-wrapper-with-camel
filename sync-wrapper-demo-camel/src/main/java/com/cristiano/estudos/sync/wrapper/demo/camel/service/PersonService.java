package com.cristiano.estudos.sync.wrapper.demo.camel.service;

import com.cristiano.estudos.sync.wrapper.demo.camel.domain.Consumer;
import com.cristiano.estudos.sync.wrapper.demo.camel.domain.Person;
import org.apache.camel.CamelContext;
import org.apache.camel.Exchange;
import org.apache.camel.ProducerTemplate;
import org.apache.camel.builder.ExchangeBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PersonService {

    private CamelContext context;
    private ProducerTemplate template;

    @Autowired
    public PersonService(CamelContext context, ProducerTemplate template) {
        this.context = context;
        this.template = template;
    }

    public Consumer convertPersonToConsumer(Person person){
        final Exchange request = ExchangeBuilder.anExchange(context).withBody(person).build();
        final Exchange response = template.send(RequestReplyRouter.ROUTE_START, request);
        Consumer consumer = response.getOut().getBody(Consumer.class);
        return consumer;
    }
}

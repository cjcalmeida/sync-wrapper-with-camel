package com.cristiano.estudos.sync.wrapper.processor.demo.adapter.listener;

import com.cristiano.estudos.sync.wrapper.processor.demo.domain.Person;
import com.cristiano.estudos.sync.wrapper.processor.demo.domain.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.jms.support.JmsHeaders;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class MessageListener {

    private PersonService service;

    @Autowired
    public MessageListener(PersonService service) {
        this.service = service;
    }

    @JmsListener(destination = "${app.queue.in}")
    public void onMessage(@Payload Person person, @Header(JmsHeaders.CORRELATION_ID) String responseId) {
        service.executeBusinessLogic(person, responseId);
    }

}

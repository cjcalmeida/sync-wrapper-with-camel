package com.cristiano.estudos.sync.wrapper.processor.demo.adapter.producer;

import com.cristiano.estudos.sync.wrapper.processor.demo.domain.Consumer;
import com.cristiano.estudos.sync.wrapper.processor.demo.domain.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Component;

@Component
public class MessageProducer implements PersonRepository {

    private JmsTemplate template;
    private final String destination;

    @Autowired
    public MessageProducer(JmsTemplate template, @Value("${app.queue.out}") String destination) {
        this.template = template;
        this.destination = destination;
    }

    public void sendResponse(Consumer person, final String responseID) {
        template.convertAndSend(destination, person, message -> {
            message.setJMSCorrelationID(responseID.toString());
            message.setJMSMessageID(responseID.toString());
            return message;
        });
    }
}

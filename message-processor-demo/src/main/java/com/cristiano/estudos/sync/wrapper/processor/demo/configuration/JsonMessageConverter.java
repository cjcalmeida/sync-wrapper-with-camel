package com.cristiano.estudos.sync.wrapper.processor.demo.configuration;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.support.converter.MessageConversionException;
import org.springframework.jms.support.converter.MessageConverter;
import org.springframework.stereotype.Component;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.Session;
import javax.jms.TextMessage;

@Component
public class JsonMessageConverter implements MessageConverter {

    private ObjectMapper mapper;

    private final Logger log = LoggerFactory.getLogger(getClass());

    @Autowired
    public JsonMessageConverter(ObjectMapper mapper) {
        this.mapper = mapper;
    }

    public Message toMessage(Object o, Session session) throws JMSException, MessageConversionException {
        var message = session.createTextMessage();
        try{
            message.setText(mapper.writeValueAsString(o));
        }catch (Exception e){
            log.error("Error", e);
        }
        return message;
    }

    public Object fromMessage(Message message) throws JMSException, MessageConversionException {
        return ((TextMessage)message).getText();
    }
}

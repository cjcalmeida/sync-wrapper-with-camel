package com.cristiano.estudos.sync.wrapper.demo.camel.service;

import com.cristiano.estudos.sync.wrapper.demo.camel.domain.Consumer;
import org.apache.camel.ExchangePattern;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.component.jackson.JacksonDataFormat;
import org.apache.camel.model.dataformat.JsonLibrary;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class RequestReplyRouter extends RouteBuilder {

    public static final String ROUTE_START = "direct:start";

    @Value("${app.queue.in}")
    private String queueInName;

    @Value("${app.queue.out}")
    private String queueOutName;

    @Override
    public void configure() throws Exception {
        JacksonDataFormat jsonFormat = new JacksonDataFormat();

        from(ROUTE_START)
                .log("Marshing JMS Message")
                .marshal()
                    .json(JsonLibrary.Jackson)

                .log("Send ${body}")
                .to(ExchangePattern.InOut, "jms:queue:"+queueInName+
                        "?replyTo="+queueOutName+
                        "&jmsMessageType=Text")
                .log("Result: ${body}")
                .unmarshal()
                    .json(JsonLibrary.Jackson, Consumer.class);
    }
}

package com.agharibi.jmsdemo.listener;


import com.agharibi.jmsdemo.config.JmsConfig;
import com.agharibi.jmsdemo.model.HelloWorldMessage;
import lombok.RequiredArgsConstructor;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.messaging.MessageHeaders;
import org.springframework.messaging.handler.annotation.Headers;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.Message;
import java.util.UUID;

@RequiredArgsConstructor
@Component
public class HelloMessageListener {

    private final JmsTemplate jmsTemplate;

    @JmsListener(destination = JmsConfig.MY_QUEUE)
    public void listen(@Payload HelloWorldMessage helloWorldMessage,
                       @Headers MessageHeaders headers,
                       Message message) {

//        System.err.println("I got a message");
//        System.out.println(helloWorldMessage);

    }


    @JmsListener(destination = JmsConfig.SEND_RECEIVE_QUEUE)
    public void listenForHello(@Payload HelloWorldMessage helloWorldMessage,
                       @Headers MessageHeaders headers,
                       Message message) throws JMSException {

        HelloWorldMessage payload = HelloWorldMessage
                .builder()
                .id(UUID.randomUUID())
                .message("world")
                .build();

        jmsTemplate.convertAndSend(message.getJMSReplyTo(), payload);


    }
}

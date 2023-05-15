package com.simbirsoft.integration.demo.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jms.core.JmsOperations;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@Slf4j
public class ActiveMQMsgProducer {

    @Value("${project.mq.producer.name}")
    private String produceQueueName;

    private final JmsOperations jmsOperations;

    public void transfer(Object message) {
        jmsOperations.convertAndSend(produceQueueName, message);
    }
}



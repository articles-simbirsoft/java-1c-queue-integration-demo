package com.simbirsoft.integration.demo.service;

import static org.springframework.messaging.MessageHeaders.TIMESTAMP;

import com.simbirsoft.integration.demo.storage.IncomeMessageStorage;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import javax.jms.JMSException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageHeaders;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@Slf4j
public class ActiveMqMsgConsumer {

    @Value("${project.mq.consumer.name}")
    private String consumeQueueName;

    private final IncomeMessageStorage messageStorage;

    @JmsListener(destination = "${project.mq.consumer.name}")
    public void receive(Message<?> message) throws JMSException {
        log.info("Message from queue '{}': {}", consumeQueueName, message);

        final Object payload = message.getPayload();
        final MessageHeaders headers = message.getHeaders();

        Long timeOfEpoch = (Long) headers.get(TIMESTAMP);

        final LocalDateTime msgTime =
            LocalDateTime.ofInstant(
                Instant.ofEpochMilli(timeOfEpoch != null ? timeOfEpoch : 0L), ZoneId.systemDefault()
            );

        messageStorage.getMessages().put(msgTime, payload);
    }

}


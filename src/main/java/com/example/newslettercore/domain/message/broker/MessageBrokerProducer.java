package com.example.newslettercore.domain.message.broker;

public interface MessageBrokerProducer {

    void sendMessage(String destination, Object message);
}

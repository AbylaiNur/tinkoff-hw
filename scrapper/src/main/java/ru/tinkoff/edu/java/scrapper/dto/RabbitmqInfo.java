package ru.tinkoff.edu.java.scrapper.dto;

public record RabbitmqInfo(
        String queueName,
        String exchangeName,
        String routingKey
) {
}

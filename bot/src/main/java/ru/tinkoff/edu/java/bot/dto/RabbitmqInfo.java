package ru.tinkoff.edu.java.bot.dto;

public record RabbitmqInfo(
        String queueName,
        String exchangeName,
        String routingKey
) {
}

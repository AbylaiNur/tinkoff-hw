package ru.tinkoff.edu.java.scrapper.client.dto.response;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.time.OffsetDateTime;

// Создание класса для nested JSON https://stackoverflow.com/questions/58305852/nested-json-objects-from-spring-restclient-and-jackson

public record GetStackoverflowQuestionsDataResponse(
        Boolean isAnswered,
        Integer viewCount,
        Integer answerCount,
        Integer score,
        String title,
        OffsetDateTime lastActivityDate,
        OffsetDateTime lastEditDate
) {
}

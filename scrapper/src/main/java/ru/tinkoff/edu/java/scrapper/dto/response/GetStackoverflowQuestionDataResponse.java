package ru.tinkoff.edu.java.scrapper.dto.response;

import java.time.OffsetDateTime;

/*
Создание класса для nested JSON
https://stackoverflow.com/questions/58305852/
nested-json-objects-from-spring-restclient-and-jackson
 */

public record GetStackoverflowQuestionDataResponse(
        Boolean isAnswered,
        Integer viewCount,
        Integer answerCount,
        Integer score,
        String title,
        OffsetDateTime lastActivityDate,
        OffsetDateTime lastEditDate
) {
}

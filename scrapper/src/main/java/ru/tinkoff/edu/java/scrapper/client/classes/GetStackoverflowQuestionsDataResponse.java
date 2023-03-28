package ru.tinkoff.edu.java.scrapper.client.classes;

public record GetStackoverflowQuestionsDataResponse(
        Boolean isAnswered,
        Integer viewCount,
        Integer answerCount,
        String title
) {
}

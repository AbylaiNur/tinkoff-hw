package ru.tinkoff.edu.java.scrapper.client;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.WebClientResponseException;
import ru.tinkoff.edu.java.scrapper.client.dto.StackoverflowApiQuestionDTO;
import ru.tinkoff.edu.java.scrapper.client.dto.response.GetStackoverflowQuestionsDataResponse;


@Service
@RequiredArgsConstructor
public class StackoverflowClient {
    private final WebClient stackoverflowWebClient;

    public GetStackoverflowQuestionsDataResponse getStackoverflowQuestionsData(String id) {
        try {
            StackoverflowApiQuestionDTO data = this.stackoverflowWebClient
                    .get()
                    .uri("/questions/{id}?order=desc&sort=activity&site=stackoverflow", id)
                    .retrieve().bodyToMono(StackoverflowApiQuestionDTO.class)
                    .block();

            GetStackoverflowQuestionsDataResponse newData = new GetStackoverflowQuestionsDataResponse(
                    data.items().get(0).isAnswered(),
                    data.items().get(0).viewCount(),
                    data.items().get(0).answerCount(),
                    data.items().get(0).score(),
                    data.items().get(0).title(),
                    data.items().get(0).lastActivityDate(),
                    data.items().get(0).lastEditDate()
            );

            return newData;
        } catch (WebClientResponseException e) {
            System.out.println(e.getMessage());
            return null;
        }
    }
}

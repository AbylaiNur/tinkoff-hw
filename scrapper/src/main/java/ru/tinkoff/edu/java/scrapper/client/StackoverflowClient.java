package ru.tinkoff.edu.java.scrapper.client;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.WebClientResponseException;
import ru.tinkoff.edu.java.scrapper.dto.StackoverflowApiQuestionDTO;
import ru.tinkoff.edu.java.scrapper.dto.response.GetStackoverflowQuestionDataResponse;


@Service
@RequiredArgsConstructor
public class StackoverflowClient {
    private final WebClient stackoverflowWebClient;

    public GetStackoverflowQuestionDataResponse getStackoverflowQuestionData(String id) {
        try {
            StackoverflowApiQuestionDTO data = this.stackoverflowWebClient
                    .get()
                    .uri("/questions/{id}?order=desc&sort=activity&site=stackoverflow", id)
                    .retrieve().bodyToMono(StackoverflowApiQuestionDTO.class)
                    .block();

            GetStackoverflowQuestionDataResponse newData = new GetStackoverflowQuestionDataResponse(
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

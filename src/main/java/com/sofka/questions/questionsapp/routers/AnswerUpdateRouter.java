package com.sofka.questions.questionsapp.routers;

import com.sofka.questions.questionsapp.model.UpdateAnswerDTO;
import com.sofka.questions.questionsapp.usecases.UpdateAnswerUseCase;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.ServerResponse;

import static org.springframework.web.reactive.function.server.RequestPredicates.PUT;
import static org.springframework.web.reactive.function.server.RequestPredicates.accept;
import static org.springframework.web.reactive.function.server.RouterFunctions.route;

@Configuration
public class AnswerUpdateRouter {

    @Bean
    RouterFunction<ServerResponse> updateAnswer(UpdateAnswerUseCase useCase){
        return route(PUT("/update-position").and(accept(MediaType.APPLICATION_JSON)),
                request -> request.bodyToMono(UpdateAnswerDTO.class)
                        .flatMap(updateAnswerDTO -> useCase.apply(updateAnswerDTO).collectList()
                                .flatMap(answerDTOList -> ServerResponse.ok()
                                        .contentType(MediaType.APPLICATION_JSON)
                                        .bodyValue(answerDTOList))
                        )
        );
    }
}

package com.sofka.questions.questionsapp.routers;

import com.sofka.questions.questionsapp.model.AnswerDTO;
import com.sofka.questions.questionsapp.usecases.AddAnswerUseCase;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.ServerResponse;

import static org.springframework.web.reactive.function.server.RequestPredicates.POST;
import static org.springframework.web.reactive.function.server.RequestPredicates.accept;
import static org.springframework.web.reactive.function.server.RouterFunctions.route;

@Configuration
public class AnswerCreateRouter {

    @Bean
    public RouterFunction<ServerResponse> saveAnswer(AddAnswerUseCase useCase){
        return route(POST("/answer").and(accept(MediaType.APPLICATION_JSON)),
                request -> request.bodyToMono(AnswerDTO.class)
                        .flatMap(answerDTO -> useCase.apply(answerDTO)
                                .flatMap(response -> ServerResponse.ok()
                                        .contentType(MediaType.APPLICATION_JSON)
                                        .bodyValue(response))
                        )
        );
    }
}

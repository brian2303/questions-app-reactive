package com.sofka.questions.questionsapp.routers;

import com.sofka.questions.questionsapp.model.QuestionDTO;
import com.sofka.questions.questionsapp.usecases.GetUseCase;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.ServerResponse;

import static org.springframework.web.reactive.function.server.RequestPredicates.*;
import static org.springframework.web.reactive.function.server.RouterFunctions.route;

@Configuration
public class QuestionGetRouter {

    @Bean
    public RouterFunction<ServerResponse> getQuestion(GetUseCase useCase){
        return route(GET("/question/{id}").and(accept(MediaType.APPLICATION_JSON)),
                request ->  ServerResponse.ok()
                        .contentType(MediaType.APPLICATION_JSON)
                        .body(BodyInserters.fromPublisher(
                                useCase.apply(request.pathVariable("id")),
                                QuestionDTO.class)
                        )
        );
    }
}

package com.sofka.questions.questionsapp.routers;

import com.sofka.questions.questionsapp.model.QuestionDTO;
import com.sofka.questions.questionsapp.usecases.AllQuestionUseCase;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.ServerResponse;

import static org.springframework.web.reactive.function.server.RequestPredicates.GET;
import static org.springframework.web.reactive.function.server.RouterFunctions.route;

@Configuration
public class QuestionGetAllRouter {

    @Bean
    public RouterFunction<ServerResponse> getAll(AllQuestionUseCase useCase){
        return route(GET("/all"),request -> ServerResponse.ok()
                .contentType(MediaType.APPLICATION_JSON)
                .body(BodyInserters.fromPublisher(useCase.get(), QuestionDTO.class))
        );
    }
}

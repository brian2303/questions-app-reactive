package com.sofka.questions.questionsapp.routers;

import com.sofka.questions.questionsapp.usecases.DeleteQuestionUseCase;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.ServerResponse;

import static org.springframework.web.reactive.function.server.RequestPredicates.DELETE;
import static org.springframework.web.reactive.function.server.RouterFunctions.route;

@Configuration
public class QuestionDeleteRouter {

    @Bean
    public RouterFunction<ServerResponse> delete(DeleteQuestionUseCase useCase){
        return route(DELETE("/delete/{id}"),
                request -> ServerResponse.ok()
                        .contentType(MediaType.APPLICATION_JSON)
                        .body(BodyInserters.fromPublisher(useCase.apply(request.pathVariable("id")),Void.class))
        );
    }

}

package com.sofka.questions.questionsapp.routers;

import com.sofka.questions.questionsapp.model.QuestionDTO;
import com.sofka.questions.questionsapp.usecases.CreateQuestionUseCase;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.server.HandlerFunction;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

import java.awt.*;
import java.util.function.Function;

import static org.springframework.web.reactive.function.server.RequestPredicates.POST;
import static org.springframework.web.reactive.function.server.RequestPredicates.accept;
import static org.springframework.web.reactive.function.server.RouterFunctions.*;

@Configuration
public class QuestionRouter {

    @Bean
    public RouterFunction<ServerResponse> create(CreateQuestionUseCase useCase){
        return route(POST("/create").and(accept(MediaType.APPLICATION_JSON)),
                request ->  request.bodyToMono(QuestionDTO.class)
                        .flatMap(questionDTO -> useCase.apply(questionDTO)
                                .flatMap(result -> ServerResponse.ok()
                                        .contentType(MediaType.TEXT_PLAIN)
                                        .bodyValue(result))
                        )
        );
    }
}

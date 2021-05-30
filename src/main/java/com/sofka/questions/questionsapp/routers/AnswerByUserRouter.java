package com.sofka.questions.questionsapp.routers;

import com.sofka.questions.questionsapp.model.AnswerUserByQuestionDTO;
import com.sofka.questions.questionsapp.model.UpdateAnswerDTO;
import com.sofka.questions.questionsapp.usecases.AnswerByUserUseCase;
import com.sofka.questions.questionsapp.usecases.UpdateAnswerUseCase;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.ServerResponse;

import static org.springframework.web.reactive.function.server.RequestPredicates.POST;
import static org.springframework.web.reactive.function.server.RequestPredicates.accept;
import static org.springframework.web.reactive.function.server.RouterFunctions.route;

@Configuration
public class AnswerByUserRouter {
    @Bean
    RouterFunction<ServerResponse> answerByUser(AnswerByUserUseCase useCase){
        return route(POST("/fetch-answer-user").and(accept(MediaType.APPLICATION_JSON)),
                request -> request.bodyToMono(AnswerUserByQuestionDTO.class)
                        .flatMap(answerUserByQuestionDTO -> useCase.apply(answerUserByQuestionDTO).collectList()
                                .flatMap(answerDTOList -> ServerResponse.ok()
                                        .contentType(MediaType.APPLICATION_JSON)
                                        .bodyValue(answerDTOList))
                        )
        );
    }
}

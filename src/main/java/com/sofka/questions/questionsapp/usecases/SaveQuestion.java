package com.sofka.questions.questionsapp.usecases;

import com.sofka.questions.questionsapp.model.QuestionDTO;
import reactor.core.publisher.Mono;

import javax.validation.Valid;

@FunctionalInterface
public interface SaveQuestion {
    Mono<String> apply(@Valid QuestionDTO questionDTO);
}

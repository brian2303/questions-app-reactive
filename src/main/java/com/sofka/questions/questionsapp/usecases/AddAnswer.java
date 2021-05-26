package com.sofka.questions.questionsapp.usecases;

import com.sofka.questions.questionsapp.model.AnswerDTO;
import com.sofka.questions.questionsapp.model.QuestionDTO;
import reactor.core.publisher.Mono;

import javax.validation.Valid;

@FunctionalInterface
public interface AddAnswer {
    Mono<QuestionDTO> apply(@Valid AnswerDTO answerDTO);
}

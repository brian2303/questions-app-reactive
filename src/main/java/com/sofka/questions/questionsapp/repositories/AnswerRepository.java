package com.sofka.questions.questionsapp.repositories;

import com.sofka.questions.questionsapp.collections.Answer;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;


@Repository
public interface AnswerRepository extends ReactiveCrudRepository<Answer, String> {

    Flux<Answer> findAllByQuestionId(String id);

    Mono<Void> deleteByQuestionId(String questionId);
}
package com.sofka.questions.questionsapp.repositories;

import com.sofka.questions.questionsapp.collections.AnswerUser;
import org.bson.types.ObjectId;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import reactor.core.publisher.Flux;

public interface AnswerUserRepository extends ReactiveCrudRepository<AnswerUser, ObjectId> {
    Flux<AnswerUser> findByUserIdAndQuestionIdOrderByDateAnswerDesc(String userId, String questionId);
}

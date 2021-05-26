package com.sofka.questions.questionsapp.usecases;

import com.sofka.questions.questionsapp.repositories.AnswerRepository;
import com.sofka.questions.questionsapp.repositories.QuestionRepository;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.util.Objects;
import java.util.function.Function;

@Service
public class DeleteQuestionUseCase implements Function<String, Mono<Void>> {


    private final QuestionRepository questionRepository;
    private final AnswerRepository answerRepository;
    private final MapperUtils mapperUtils;

    public DeleteQuestionUseCase(QuestionRepository questionRepository, AnswerRepository answerRepository, MapperUtils mapperUtils) {
        this.questionRepository = questionRepository;
        this.answerRepository = answerRepository;
        this.mapperUtils = mapperUtils;
    }

    @Override
    public Mono<Void> apply(String id) {
        Objects.requireNonNull(id,"Id is required");
        return questionRepository.deleteById(id)
                .switchIfEmpty(Mono.defer(() -> answerRepository.deleteByQuestionId(id)));
    }
}

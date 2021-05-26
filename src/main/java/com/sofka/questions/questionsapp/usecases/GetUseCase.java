package com.sofka.questions.questionsapp.usecases;

import com.sofka.questions.questionsapp.model.QuestionDTO;
import com.sofka.questions.questionsapp.repositories.AnswerRepository;
import com.sofka.questions.questionsapp.repositories.QuestionRepository;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;
import reactor.core.publisher.Mono;

import java.util.Objects;
import java.util.function.Function;

@Service
@Validated
public class GetUseCase implements Function<String, Mono<QuestionDTO>> {
    private final QuestionRepository questionRepository;
    private final AnswerRepository answerRepository;
    private final MapperUtils mapperUtils;

    public GetUseCase(MapperUtils mapperUtils, QuestionRepository questionRepository, AnswerRepository answerRepository) {
        this.questionRepository = questionRepository;
        this.answerRepository = answerRepository;
        this.mapperUtils = mapperUtils;
    }

    @Override
    public Mono<QuestionDTO> apply(String id) {
        Objects.requireNonNull(id, "Id is required");
        return questionRepository.findById(id)
                .map(mapperUtils.mapEntityToQuestion())
                .flatMap(mapQuestionAggregate());
    }

    private Function<QuestionDTO, Mono<QuestionDTO>> mapQuestionAggregate() {
        return questionDTO ->
                Mono.just(questionDTO).zipWith(
                        answerRepository.findAllByQuestionId(questionDTO.getId())
                                .map(mapperUtils.mapEntityToAnswer())
                                .collectList(),
                        (question, answers) -> {
                            question.setAnswers(answers);
                            return question;
                        }
                );
    }

}

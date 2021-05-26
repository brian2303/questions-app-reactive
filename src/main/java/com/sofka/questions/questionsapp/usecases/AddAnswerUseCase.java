package com.sofka.questions.questionsapp.usecases;

import com.sofka.questions.questionsapp.model.AnswerDTO;
import com.sofka.questions.questionsapp.model.QuestionDTO;
import com.sofka.questions.questionsapp.repositories.AnswerRepository;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;
import reactor.core.publisher.Mono;

@Service
@Validated
public class AddAnswerUseCase implements AddAnswer {

    private final MapperUtils mapperUtils;
    private final GetUseCase getUseCase;
    private final AnswerRepository answerRepository;

    public AddAnswerUseCase(MapperUtils mapperUtils, GetUseCase getUseCase, AnswerRepository answerRepository) {
        this.mapperUtils = mapperUtils;
        this.getUseCase = getUseCase;
        this.answerRepository = answerRepository;
    }

    @Override
    public Mono<QuestionDTO> apply(AnswerDTO answerDTO) {
        return getUseCase.apply(answerDTO.getQuestionId())
                .flatMap(questionDTO -> answerRepository.save(mapperUtils.mapperToAnswer().apply(answerDTO))
                        .map(answer -> {
                            questionDTO.getAnswers().add(mapperUtils.mapEntityToAnswer().apply(answer));
                            return questionDTO;
                        }));
    }
}

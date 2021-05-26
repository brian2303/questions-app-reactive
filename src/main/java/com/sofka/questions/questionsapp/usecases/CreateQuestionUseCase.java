package com.sofka.questions.questionsapp.usecases;

import com.sofka.questions.questionsapp.collections.Question;
import com.sofka.questions.questionsapp.model.QuestionDTO;
import com.sofka.questions.questionsapp.repositories.QuestionRepository;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;
import reactor.core.publisher.Mono;

import javax.validation.Valid;

@Service
@Validated
public class CreateQuestionUseCase implements SaveQuestion{


    private final QuestionRepository questionRepository;
    private final MapperUtils mapperUtils;

    public CreateQuestionUseCase(QuestionRepository questionRepository,MapperUtils mapperUtils) {
        this.questionRepository = questionRepository;
        this.mapperUtils = mapperUtils;
    }

    @Override
    public Mono<String> apply(@Valid QuestionDTO questionDTO) {
        return questionRepository.save(mapperUtils.mapperToQuestion(null).apply(questionDTO))
                .map(Question::getId);
    }
}

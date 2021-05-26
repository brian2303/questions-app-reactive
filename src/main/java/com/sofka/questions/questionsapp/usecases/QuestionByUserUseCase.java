package com.sofka.questions.questionsapp.usecases;

import com.sofka.questions.questionsapp.model.QuestionDTO;
import com.sofka.questions.questionsapp.repositories.QuestionRepository;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;
import reactor.core.publisher.Flux;

import java.util.function.Function;

@Service
public class QuestionByUserUseCase implements Function<String,Flux<QuestionDTO>> {

    private final QuestionRepository repository;
    private final MapperUtils mapperUtils;

    public QuestionByUserUseCase(QuestionRepository repository, MapperUtils mapperUtils) {
        this.repository = repository;
        this.mapperUtils = mapperUtils;
    }

    @Override
    public Flux<QuestionDTO> apply(String userId) {
        return repository.findByUserId(userId)
                .map(mapperUtils.mapEntityToQuestion());
    }
}

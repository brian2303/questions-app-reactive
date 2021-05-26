package com.sofka.questions.questionsapp.usecases;

import com.sofka.questions.questionsapp.model.QuestionDTO;
import com.sofka.questions.questionsapp.repositories.QuestionRepository;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;

import java.util.function.Supplier;

@Service
public class AllQuestionUseCase implements Supplier<Flux<QuestionDTO>> {

    private final QuestionRepository repository;
    private final MapperUtils mapperUtils;

    public AllQuestionUseCase(QuestionRepository repository, MapperUtils mapperUtils) {
        this.repository = repository;
        this.mapperUtils = mapperUtils;
    }

    @Override
    public Flux<QuestionDTO> get() {
        return repository.findAll()
                .map(mapperUtils.mapEntityToQuestion());
    }
}

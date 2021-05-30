package com.sofka.questions.questionsapp.usecases;

import com.sofka.questions.questionsapp.model.AnswerUserByQuestionDTO;
import com.sofka.questions.questionsapp.model.UpdateAnswerDTO;
import com.sofka.questions.questionsapp.repositories.AnswerUserRepository;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;

import java.util.function.Function;

@Service
public class AnswerByUserUseCase implements Function<AnswerUserByQuestionDTO, Flux<UpdateAnswerDTO>> {

    private final MapperUtils mapperUtils;
    private final AnswerUserRepository answerUserRepository;

    public AnswerByUserUseCase(MapperUtils mapperUtils, AnswerUserRepository answerUserRepository) {
        this.mapperUtils = mapperUtils;
        this.answerUserRepository = answerUserRepository;
    }


    @Override
    public Flux<UpdateAnswerDTO> apply(AnswerUserByQuestionDTO answerUserByQuestionDTO) {
        return mapperUtils.mapFluxEntityToFluxAnswerUser().apply(answerUserRepository
                .findByUserIdAndQuestionIdOrderByDateAnswerDesc(answerUserByQuestionDTO.getUserId(),answerUserByQuestionDTO.getQuestionId())
        );
    }
}

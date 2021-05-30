package com.sofka.questions.questionsapp.usecases;

import com.sofka.questions.questionsapp.collections.Answer;
import com.sofka.questions.questionsapp.model.AnswerUserByQuestionDTO;
import com.sofka.questions.questionsapp.model.UpdateAnswerDTO;
import com.sofka.questions.questionsapp.repositories.AnswerRepository;
import com.sofka.questions.questionsapp.repositories.AnswerUserRepository;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.Optional;
import java.util.function.Function;

@Service
public class UpdateAnswerUseCase implements Function<UpdateAnswerDTO, Flux<UpdateAnswerDTO>> {
    private final MapperUtils mapperUtils;
    private final AnswerRepository answerRepository;
    private final AnswerByUserUseCase useCase;
    private final AnswerUserRepository answerUserRepository;


    public UpdateAnswerUseCase(MapperUtils mapperUtils, AnswerRepository answerRepository, AnswerByUserUseCase useCase, AnswerUserRepository answerUserRepository) {
        this.mapperUtils = mapperUtils;
        this.answerRepository = answerRepository;
        this.useCase = useCase;
        this.answerUserRepository = answerUserRepository;
    }

    @Override
    public Flux<UpdateAnswerDTO> apply(UpdateAnswerDTO answerActionDTO) {
        return Mono.just(answerActionDTO)
                .flatMap(updateAnswer -> answerRepository.findById(updateAnswer.getAnswerId()))
                .flatMap(answerResponse -> updateAnswer(answerResponse, answerActionDTO.getAction()))
                .flatMap(answer -> answerUserRepository.save(mapperUtils.mapDTOToAnswerUser().apply(answerActionDTO)))
                .flatMapMany(answer -> useCase.apply(new AnswerUserByQuestionDTO(answer.getUserId(),answer.getQuestionId())));
    }

    public Mono<Answer> updateAnswer(Answer answer, String action) {
        return Optional.of(answer)
                .filter(a -> action.equalsIgnoreCase("sum"))
                .map(a -> {
                    a.setPosition(a.getPosition() + 1);
                    return answerRepository.save(a);
                }).orElseGet(() -> {
                    answer.setPosition(answer.getPosition() - 1);
                    return answerRepository.save(answer);
                });
    }
}

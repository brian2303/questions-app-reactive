package com.sofka.questions.questionsapp.usecases;

import com.sofka.questions.questionsapp.collections.Answer;
import com.sofka.questions.questionsapp.model.AnswerDTO;
import com.sofka.questions.questionsapp.model.QuestionDTO;
import com.sofka.questions.questionsapp.model.UpdateAnswerDTO;
import com.sofka.questions.questionsapp.repositories.AnswerRepository;
import com.sofka.questions.questionsapp.repositories.QuestionRepository;
import reactor.core.publisher.Mono;

import java.util.Optional;
import java.util.function.Function;

public class UpdateAnswerUseCase implements Function<Mono<UpdateAnswerDTO>, Mono<QuestionDTO>> {
    private final MapperUtils mapperUtils;
    private final AnswerRepository answerRepository;
    private final QuestionRepository questionRepository;
    private final GetUseCase getUseCase;

    public UpdateAnswerUseCase(MapperUtils mapperUtils, AnswerRepository answerRepository, QuestionRepository questionRepository, GetUseCase getUseCase) {
        this.mapperUtils = mapperUtils;
        this.answerRepository = answerRepository;
        this.questionRepository = questionRepository;
        this.getUseCase = getUseCase;
    }

    @Override
    public Mono<QuestionDTO> apply(Mono<UpdateAnswerDTO> answerActionDTO) {
        return answerActionDTO.flatMap(updateAnswer -> answerRepository.findById(updateAnswer.getAnswerId())
                .flatMap(answerResponse -> updateAnswer(answerResponse, updateAnswer.getAction())
                        .flatMap(answerResp -> getUseCase.apply(updateAnswer.getQuestionId()))
                )
        );
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

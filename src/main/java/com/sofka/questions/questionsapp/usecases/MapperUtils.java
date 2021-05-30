package com.sofka.questions.questionsapp.usecases;

import com.sofka.questions.questionsapp.collections.Answer;
import com.sofka.questions.questionsapp.collections.AnswerUser;
import com.sofka.questions.questionsapp.collections.Question;
import com.sofka.questions.questionsapp.model.AnswerDTO;
import com.sofka.questions.questionsapp.model.QuestionDTO;
import com.sofka.questions.questionsapp.model.UpdateAnswerDTO;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.LocalDateTime;
import java.util.function.Function;

@Component
public class MapperUtils {

    public Function<AnswerDTO, Answer> mapperToAnswer() {
        return updateAnswer -> {
            var answer = new Answer();
            answer.setPosition(updateAnswer.getPosition());
            answer.setQuestionId(updateAnswer.getQuestionId());
            answer.setUserId(updateAnswer.getUserId());
            answer.setAnswer(updateAnswer.getAnswer());
            return answer;
        };
    }

    public Function<QuestionDTO, Question> mapperToQuestion(String id) {
        return updateQuestion -> {
            var question = new Question();
            question.setId(id);
            question.setUserId(updateQuestion.getUserId());
            question.setCategory(updateQuestion.getCategory());
            question.setQuestion(updateQuestion.getQuestion());
            question.setUserId(updateQuestion.getUserId());
            question.setType(updateQuestion.getType());
            return question;
        };
    }

    public Function<Question, QuestionDTO> mapEntityToQuestion() {
        return entity -> new QuestionDTO(
                entity.getId(),
                entity.getUserId(),
                entity.getQuestion(),
                entity.getType(),
                entity.getCategory()
        );
    }

    public Function<Answer, AnswerDTO> mapEntityToAnswer() {
        return entity -> new AnswerDTO(
                entity.getQuestionId(),
                entity.getUserId(),
                entity.getAnswer(),
                entity.getPosition(),
                entity.getId());
    }

    public Function<UpdateAnswerDTO,AnswerUser> mapDTOToAnswerUser(){
        return dto -> new AnswerUser(
                dto.getQuestionId(),
                dto.getAnswerId(),
                dto.getAction(),
                dto.getUserId(),
                LocalDateTime.now(),
                dto.getUpdate()

            );
    }


    public Function<Flux<AnswerUser>,Flux<UpdateAnswerDTO>> mapFluxEntityToFluxAnswerUser(){
        return fluxAnswerUser -> fluxAnswerUser
                .flatMap(answerUser -> Mono.just(
                        new UpdateAnswerDTO(
                                answerUser.getUpdate(),
                                answerUser.getQuestionId(),
                                answerUser.getAnswerId(),
                                answerUser.getAction(),
                                answerUser.getUserId(),
                                answerUser.getDateAnswer())
                ));
    }
}
package com.sofka.questions.questionsapp.usecases;

import com.sofka.questions.questionsapp.collections.Answer;
import com.sofka.questions.questionsapp.model.AnswerDTO;
import com.sofka.questions.questionsapp.model.QuestionDTO;
import com.sofka.questions.questionsapp.repositories.AnswerRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.mock.mockito.SpyBean;
import reactor.core.publisher.Mono;

@SpringBootTest
class AddAnswerUseCaseTest {

    @SpyBean
    AddAnswerUseCase addAnswerUseCase;

    @MockBean
    GetUseCase getUseCase;

    @MockBean
    AnswerRepository answerRepository;

    @Test
    void answerTest(){

        var questionDTO = new QuestionDTO("01","u01","test?","test","test");
        var answerDTO = new AnswerDTO("01","u01","test",0);
        var answer = new Answer();

        answer.setId("01");
        answer.setQuestionId("01");
        answer.setUserId("u01");
        answer.setAnswer("test");

        Mockito.when(answerRepository.save(Mockito.any(Answer.class))).thenReturn(Mono.just(answer));
        Mockito.when(getUseCase.apply(Mockito.anyString())).thenReturn(Mono.just(questionDTO));

        var resultQuestionDTO = addAnswerUseCase.apply(answerDTO).block();

        Assertions.assertEquals(resultQuestionDTO.getId(),questionDTO.getId());
        Assertions.assertEquals(resultQuestionDTO.getQuestion(),questionDTO.getQuestion());
        Assertions.assertEquals(resultQuestionDTO.getAnswers().get(0).getId(),answerDTO.getId());

    }


}
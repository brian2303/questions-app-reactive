package com.sofka.questions.questionsapp.usecases;

import com.sofka.questions.questionsapp.collections.Question;
import com.sofka.questions.questionsapp.model.QuestionDTO;
import com.sofka.questions.questionsapp.repositories.QuestionRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.mock.mockito.SpyBean;
import reactor.core.publisher.Mono;



@SpringBootTest
class CreateQuestionUseCaseTest{

    @MockBean
    QuestionRepository questionRepository;

    @SpyBean
    CreateQuestionUseCase createQuestionUseCase;

    @Test
    void createQuestion(){
        var question = new Question();
        question.setId("2223");
        question.setUserId("u01");
        question.setQuestion("question1");
        question.setCategory("cat01");
        question.setType("type01");

        var questionDTO = new QuestionDTO();
        questionDTO.setId("2223");
        questionDTO.setType("type01");
        questionDTO.setQuestion("question1");
        questionDTO.setCategory("cat01");
        questionDTO.setUserId("u01");

        Mockito.when(questionRepository.save(Mockito.any(Question.class))).thenReturn(Mono.just(question));
        var idQuestion = createQuestionUseCase.apply(questionDTO);

        Assertions.assertEquals(idQuestion.block(),questionDTO.getId());
    }

}
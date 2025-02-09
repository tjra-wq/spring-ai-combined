package com.tjr.springaicombined.services.intro;

import com.tjr.springaicombined.model.Answer;
import com.tjr.springaicombined.model.Question;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class OpenAiIntroServiceImplTest {

    @Autowired
    OpenAiIntroService openAiIntroService;

    @Test
    void getAnswer() {
        Answer answer = openAiIntroService.getAnswer(new Question("Write a python program to add two numbers"));
        System.out.println("Answer: " + answer.answer());

        Answer answer2 = openAiIntroService.getAnswer(new Question("Write the game snake and ladder in python"));
        System.out.println("Answer: " + answer2.answer());

    }
}
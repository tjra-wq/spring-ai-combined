package com.tjr.springaicombined.Controllers;

import com.tjr.springaicombined.model.Answer;
import com.tjr.springaicombined.model.Question;
import com.tjr.springaicombined.model.intro.GetCapitalRequest;
import com.tjr.springaicombined.model.intro.GetCapitalResponse;
import com.tjr.springaicombined.model.intro.GetCapitalWithInfoResponse;
import com.tjr.springaicombined.services.intro.OpenAiIntroService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("intro/questions")
@RequiredArgsConstructor
public class OpenAiIntoQuestionsController {

    private final OpenAiIntroService openAiIntroService;

    @PostMapping("/ask")
    public Answer askQuestion(@RequestBody Question question) {
        return openAiIntroService.getAnswer(question);
    }

    @PostMapping("/getCapital")
    public GetCapitalResponse getCapital(@RequestBody GetCapitalRequest getCapitalRequest) {
        return openAiIntroService.getCapital(getCapitalRequest);
    }

    @PostMapping("/getCapitalWithInfo")
    public GetCapitalWithInfoResponse getCapitalWithInfo(@RequestBody GetCapitalRequest getCapitalRequest) {
        return openAiIntroService.getCapitalWithInfo(getCapitalRequest);
    }

}

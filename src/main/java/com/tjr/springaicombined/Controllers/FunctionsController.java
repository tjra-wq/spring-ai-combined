package com.tjr.springaicombined.Controllers;

import com.tjr.springaicombined.model.Answer;
import com.tjr.springaicombined.model.Question;
import com.tjr.springaicombined.services.functions.OpenAIFunctionsService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("functions")
public class FunctionsController {

    private final OpenAIFunctionsService openAIFunctionsService;

    @PostMapping("/weather")
    public Answer askQuestion(@RequestBody Question question) {
        return openAIFunctionsService.getAnswer(question);
    }

    @PostMapping("/stock")
    public Answer getStockPrice(@RequestBody Question question) {
        return openAIFunctionsService.getStockPrice(question);
    }
}

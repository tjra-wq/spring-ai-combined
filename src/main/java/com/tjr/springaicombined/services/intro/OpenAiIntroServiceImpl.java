package com.tjr.springaicombined.services.intro;

import com.tjr.springaicombined.model.Answer;
import com.tjr.springaicombined.model.Question;
import com.tjr.springaicombined.model.intro.GetCapitalRequest;
import com.tjr.springaicombined.model.intro.GetCapitalResponse;
import com.tjr.springaicombined.model.intro.GetCapitalWithInfoResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.ai.chat.model.ChatModel;
import org.springframework.ai.chat.model.ChatResponse;
import org.springframework.ai.chat.prompt.Prompt;
import org.springframework.ai.chat.prompt.PromptTemplate;
import org.springframework.ai.converter.BeanOutputConverter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
@Slf4j
@RequiredArgsConstructor
public class OpenAiIntroServiceImpl implements OpenAiIntroService {

    private final ChatModel chatModel;

    @Value("classpath:templates/intro/get-capital-prompt.st")
    private Resource getCapitalPrompt;

//    @Value("classpath:templates/get-capital-with-info-prompt.st")
//    private Resource getCapitalWithInfoPrompt;

//    @Autowired
//    ObjectMapper objectMapper;

    @Override
    public Answer getAnswer(Question question) {
        log.info("Question: {}", question);
        PromptTemplate promptTemplate = new PromptTemplate(question.question());
        Prompt prompt = promptTemplate.create();

        ChatResponse chatResponse = chatModel.call(prompt);
        return new Answer(chatResponse.getResult().getOutput().getContent());
    }

    @Override
    public GetCapitalResponse getCapital(GetCapitalRequest getCapitalRequest) {
        BeanOutputConverter<GetCapitalResponse> beanOutputConverter = new BeanOutputConverter<>(GetCapitalResponse.class);
        String format = beanOutputConverter.getFormat();
        log.info("Question (answer will be in json): {}", getCapitalRequest);

        PromptTemplate promptTemplate = new PromptTemplate(getCapitalPrompt);
        Prompt prompt = promptTemplate.create(Map.of("stateOrCountry", getCapitalRequest.stateOrCountry(),
                                                    "format", format));

        ChatResponse chatResponse = chatModel.call(prompt);

        return beanOutputConverter.convert(chatResponse.getResult().getOutput().getContent());
    }

    @Override
    public GetCapitalWithInfoResponse getCapitalWithInfo(GetCapitalRequest getCapitalRequest) {
        log.info("Question: {}", getCapitalRequest);
        BeanOutputConverter<GetCapitalWithInfoResponse> beanOutputConverter = new BeanOutputConverter<>(GetCapitalWithInfoResponse.class);
        String format = beanOutputConverter.getFormat();

        PromptTemplate promptTemplate = new PromptTemplate(getCapitalPrompt);
        Prompt prompt = promptTemplate.create(Map.of("stateOrCountry", getCapitalRequest.stateOrCountry(),
                                                    "format", format));
        ChatResponse chatResponse = chatModel.call(prompt);

        return beanOutputConverter.convert(chatResponse.getResult().getOutput().getContent());
    }
}

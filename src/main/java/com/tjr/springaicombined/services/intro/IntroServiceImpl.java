package com.tjr.springaicombined.services.intro;

import com.tjr.springaicombined.model.Answer;
import com.tjr.springaicombined.model.Question;
import com.tjr.springaicombined.model.intro.GetCapitalRequest;
import com.tjr.springaicombined.model.intro.GetCapitalResponse;
import com.tjr.springaicombined.model.intro.GetCapitalWithInfoResponse;
import groovy.util.logging.Slf4j;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
public class IntroServiceImpl implements IntroService {

    private final ChatModel chatModel;

    private static final Logger log = LoggerFactory.getLogger(IntroServiceImpl.class);

    @Value("classpath:templates/intro/get-capital-prompt.st")
    private Resource getCapitalPrompt;

//    not required because we have model for this
//    @Value("classpath:templates/get-capital-with-info-prompt.st")
//    private Resource getCapitalWithInfoPrompt;

//    @Autowired
//    ObjectMapper objectMapper;

    @Override
    public String getAnswer(String question) {
        log.info("Question: " + question);
        PromptTemplate promptTemplate = new PromptTemplate(question);
        Prompt prompt = promptTemplate.create();

        ChatResponse chatResponse = chatModel.call(prompt);
        return chatResponse.getResult().getOutput().getContent();
    }

    @Override
    public Answer getAnswer(Question question) {
        log.info("Question: " + question);
        PromptTemplate promptTemplate = new PromptTemplate(question.question());
        Prompt prompt = promptTemplate.create();

        ChatResponse chatResponse = chatModel.call(prompt);
        return new Answer(chatResponse.getResult().getOutput().getContent());
    }

    @Override
    public GetCapitalResponse getCapital(GetCapitalRequest getCapitalRequest) {
        BeanOutputConverter<GetCapitalResponse> beanOutputConverter = new BeanOutputConverter<>(GetCapitalResponse.class);
        String format = beanOutputConverter.getFormat();
        log.info("Question (answer will be in json): " + getCapitalRequest);

        PromptTemplate promptTemplate = new PromptTemplate(getCapitalPrompt);
        Prompt prompt = promptTemplate.create(Map.of("stateOrCountry", getCapitalRequest.stateOrCountry(),
                                                    "format", format));

        ChatResponse chatResponse = chatModel.call(prompt);

        return beanOutputConverter.convert(chatResponse.getResult().getOutput().getContent());
    }

    @Override
    public GetCapitalWithInfoResponse getCapitalWithInfo(GetCapitalRequest getCapitalRequest) {
        log.info("Question: " + getCapitalRequest);
        BeanOutputConverter<GetCapitalWithInfoResponse> beanOutputConverter = new BeanOutputConverter<>(GetCapitalWithInfoResponse.class);
        String format = beanOutputConverter.getFormat();

        PromptTemplate promptTemplate = new PromptTemplate(getCapitalPrompt);
        Prompt prompt = promptTemplate.create(Map.of("stateOrCountry", getCapitalRequest.stateOrCountry(),
                                                    "format", format));
        ChatResponse chatResponse = chatModel.call(prompt);

        return beanOutputConverter.convert(chatResponse.getResult().getOutput().getContent());
    }
}

package com.tjr.springaicombined.services.functions;

import com.tjr.springaicombined.model.Answer;
import com.tjr.springaicombined.model.Question;

public interface OpenAIFunctionsService {
    Answer getAnswer(Question question);

    Answer getStockPrice(Question question);
}

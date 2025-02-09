package com.tjr.springaicombined.services.intro;

import com.tjr.springaicombined.model.Answer;
import com.tjr.springaicombined.model.Question;
import com.tjr.springaicombined.model.intro.GetCapitalRequest;
import com.tjr.springaicombined.model.intro.GetCapitalResponse;
import com.tjr.springaicombined.model.intro.GetCapitalWithInfoResponse;

public interface OpenAiIntroService {
//    String getAnswer(String question);
    Answer getAnswer(Question question);
    GetCapitalResponse getCapital(GetCapitalRequest stateOrCountry);
    GetCapitalWithInfoResponse getCapitalWithInfo(GetCapitalRequest getCapitalRequest);
}

package com.tjr.springaiintro.services;

import com.tjr.springaiintro.model.*;

public interface OpenAIService {
    String getAnswer(String question);
    Answer getAnswer(Question question);
    GetCapitalResponse getCapital(GetCapitalRequest stateOrCountry);
    GetCapitalWithInfoResponse getCapitalWithInfo(GetCapitalRequest getCapitalRequest);
}

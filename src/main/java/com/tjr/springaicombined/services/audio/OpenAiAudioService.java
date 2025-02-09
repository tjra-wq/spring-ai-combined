package com.tjr.springaicombined.services.audio;

import com.tjr.springaicombined.model.Question;

public interface OpenAiAudioService {
    byte[] convertTextToSpeech(Question question);
}

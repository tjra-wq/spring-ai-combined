package com.tjr.springaicombined.services.audio;

import com.tjr.springaicombined.model.Question;
import lombok.RequiredArgsConstructor;
import org.springframework.ai.openai.OpenAiAudioSpeechModel;
import org.springframework.ai.openai.OpenAiAudioSpeechOptions;
import org.springframework.ai.openai.api.OpenAiAudioApi;
import org.springframework.ai.openai.audio.speech.SpeechPrompt;
import org.springframework.ai.openai.audio.speech.SpeechResponse;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class OpenAiAudioServiceImpl implements OpenAiAudioService{

    private final OpenAiAudioSpeechModel openAiAudioSpeechModel;

    @Override
    public byte[] convertTextToSpeech(Question question) {
        OpenAiAudioSpeechOptions speechOptions = OpenAiAudioSpeechOptions.builder()
                .voice(OpenAiAudioApi.SpeechRequest.Voice.NOVA)
                .speed(1.0f)
                .responseFormat(OpenAiAudioApi.SpeechRequest.AudioResponseFormat.MP3)
                .model(OpenAiAudioApi.TtsModel.TTS_1.value)
                .build();

        SpeechPrompt speechPrompt = new SpeechPrompt(question.question(),speechOptions);
        SpeechResponse speechResponse = openAiAudioSpeechModel.call(speechPrompt);
        return speechResponse.getResult().getOutput();
    }
}

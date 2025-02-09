package com.tjr.springaicombined.Controllers;

import com.tjr.springaicombined.model.Question;
import com.tjr.springaicombined.services.audio.OpenAiAudioService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("audio")
public class AudioController {

    private final OpenAiAudioService openAiAudioService;

    @PostMapping(value = "/convertTextToSpeech", produces = "audio/mpeg")
    public byte[] convertTextToSpeech(@RequestBody Question question) {
        return openAiAudioService.convertTextToSpeech(question);
    }
}

package com.tjr.springaicombined.Controllers;

import com.tjr.springaicombined.model.Question;
import com.tjr.springaicombined.services.image.OpenAiImageService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequiredArgsConstructor
@RequestMapping("generate")
public class ImageController {
    private final OpenAiImageService openAiImageService;

    @PostMapping(value = "/image", produces = MediaType.IMAGE_PNG_VALUE)
    public byte[] getImage(@RequestBody Question question) {
        return openAiImageService.getImage(question);
    }

    @PostMapping(value = "/imageDescription",
            consumes = {MediaType.MULTIPART_FORM_DATA_VALUE},
            produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<String> uploadImageAndGetDescription(
            @Validated @RequestParam("file") MultipartFile file) {
        return ResponseEntity.ok(openAiImageService.getDescription(file));
    }
}

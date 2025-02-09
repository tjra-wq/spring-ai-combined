package com.tjr.springaicombined.services.image;

import com.tjr.springaicombined.model.Question;
import org.springframework.web.multipart.MultipartFile;

public interface OpenAiImageService {
    byte[] getImage(Question question);
    String getDescription(MultipartFile file);
}

package com.springai;

import org.springframework.ai.image.ImagePrompt;
import org.springframework.ai.image.ImageResponse;
import org.springframework.ai.openai.OpenAiImageModel;
import org.springframework.ai.openai.OpenAiImageOptions;
import org.springframework.stereotype.Service;

import java.io.IOException;


@Service
@SuppressWarnings("All")
public class ImageService {

    private final OpenAiImageModel openAiImageModel;

    public ImageService(OpenAiImageModel openAiImageModel) {
        this.openAiImageModel = openAiImageModel;
    }

    public ImageResponse generateImage(String prompt, String quality, int n, int width, int height)throws IOException {
//       ImageResponse imageResponse = openAiImageModel.call(
//                new ImagePrompt(prompt)
//        );
//       return imageResponse;


//        ImageResponse imageResponse = openAiImageModel.call(
//                new ImagePrompt(prompt,
//                        OpenAiImageOptions.builder()
//                                .model("dall-e-2")
//                                .quality("hd")
//                                .N(4)      // here N is number or images. higher the image create ..higher the token will be use and then cost u.
//                                .height(1024)
//                                .width(1024)
//                                .build())
//        );

        ImageResponse imageResponse = openAiImageModel.call(
                new ImagePrompt(prompt,
                        OpenAiImageOptions.builder()
                                .model("dall-e-2")
                                .quality(quality)
                                .N(n)      // here N is number or images. higher the image create ..higher the token will be use and then cost u.
                                .height(height)
                                .width(width)
                                .build())
        );
        return imageResponse;
    }
}


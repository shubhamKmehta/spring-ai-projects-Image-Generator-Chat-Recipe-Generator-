package com.springai;

import jakarta.servlet.http.HttpServletResponse;
import org.springframework.ai.image.ImageResponse;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.List;

@SuppressWarnings("All")
@RestController
public class GenAIController {
    private final ChatService chatService;
    private  final ImageService imageService;
    private final RecipeService recipeService;

    public GenAIController(ChatService chatService, ImageService imageService, RecipeService recipeService){
        this.chatService=chatService;
        this.imageService = imageService;
        this.recipeService = recipeService;
    }

    @GetMapping("/ask-ai")
    public String getResponse(@RequestParam String prompt){
        return chatService.getResponse(prompt);
    }

    @GetMapping("/ask-ai-options")
    public String getResponseOptions(@RequestParam String prompt){
        return chatService.getResponseOptions(prompt);
    }


/*
    @GetMapping("/generate-image")
     public void generateImage(HttpServletResponse response, @RequestParam String prompt) throws IOException {
        ImageResponse imageResponse = imageService.generateImage(prompt);
        String url = imageResponse.getResult().getOutput().getUrl();  // if u r extracting multiple image then u get also multiple url for images.
        response.sendRedirect(url);
    }
*/

    @GetMapping("/generate-image")
    public List<String> generateImages(HttpServletResponse response,
                                       @RequestParam String prompt,
                                       @RequestParam(value = "quality",defaultValue = "hd") String quality,
                                       @RequestParam(defaultValue = "1") int n,
                                       @RequestParam(defaultValue = "1024") int width,
                                       @RequestParam(defaultValue = "1024") int height)throws IOException {
        ImageResponse imageResponse = imageService.generateImage(prompt,quality,n,width,height);

        // streams to get urls from imageResponse

        return imageResponse.getResults().stream()
                .map(result -> result.getOutput().getUrl())
                .toList();

//        return imageUrl;

    }
    @GetMapping("/recipe-creator")
    public String recipeCreate(@RequestParam String ingredients,
                                     @RequestParam(defaultValue = "any") String cuisine,
                                     @RequestParam(defaultValue = "") String dietaryRestrictions){
        return recipeService.createRecipe(ingredients , cuisine,dietaryRestrictions);
    }
}

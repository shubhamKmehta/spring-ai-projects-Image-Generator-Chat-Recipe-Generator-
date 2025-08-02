package com.springai;

import org.springframework.ai.chat.messages.Message;
import org.springframework.ai.chat.model.ChatModel;
import org.springframework.ai.chat.model.ChatResponse;
import org.springframework.ai.chat.prompt.Prompt;
import org.springframework.ai.openai.OpenAiChatOptions;
import org.springframework.stereotype.Service;

@Service
public class ChatService {
    private final ChatModel chatModel;

    public ChatService(ChatModel chatModel){
        this.chatModel = chatModel;
    }

    public String getResponse(String prompt){
        return chatModel.call();
    }

    public String getResponseOptions(String prompt){
        ChatResponse chatResponse =  chatModel.call(
                (Prompt) new Prompt(
                        prompt,
                        OpenAiChatOptions.builder()
                                .model("gpt-4-o")
                                .temperature(0.4)
                                .build()
                )
        );
        return chatResponse.getResult().getOutput().getText();
    }
}

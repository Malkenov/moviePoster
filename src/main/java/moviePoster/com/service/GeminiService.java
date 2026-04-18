package moviePoster.com.service;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.stereotype.Service;

@Service
public class GeminiService {

    private final ChatClient chatClient;

    public GeminiService(ChatClient.Builder chatClientBuilder) {
        this.chatClient = chatClientBuilder.build();
    }

    public String generateReview(String movieTitle) {
        return chatClient.prompt()
                .user("Напиши краткий обзор фильма: " + movieTitle)
                .call()
                .content();
    }
}
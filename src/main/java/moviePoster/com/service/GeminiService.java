package moviePoster.com.service;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.stereotype.Service;

@Service
public class GeminiService {

    private final ChatClient chatClient;

    public GeminiService(ChatClient.Builder chatClientBuilder) {
        this.chatClient = chatClientBuilder.build();
    }

    // Тут генерация краткого обзора фильма
    public String generateReview(String movieTitle) {
        return chatClient.prompt()
                .user("Напиши краткий обзор фильма \"" + movieTitle + "\" на русском языке. " +
                        "Укажи жанр, основной сюжет и стоит ли смотреть. Максимум 150 слов.")
                .call()
                .content();
    }

    // Тут рекомендация похожих фильмов
    public String recommendSimilar(String movieTitle) {
        return chatClient.prompt()
                .user("Порекомендуй 5 фильмов похожих на \"" + movieTitle + "\". " +
                        "Для каждого укажи название и одно предложение почему стоит посмотреть. На русском языке.")
                .call()
                .content();
    }
}
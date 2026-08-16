package moviePoster.com.service;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.timelimiter.annotation.TimeLimiter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.stereotype.Service;

import java.util.concurrent.CompletableFuture;

@Slf4j
@Service
@RequiredArgsConstructor
public class MovieDescriptionService {

    private final ChatClient chatClient;

    @CircuitBreaker(name = "geminiAi", fallbackMethod = "fallbackDescription")
    @TimeLimiter(name = "geminiAi")
    public CompletableFuture<String> generateDescription(String movieTitle) {
        return CompletableFuture.supplyAsync(() ->
                chatClient.prompt()
                        .user("Напиши короткое описание к фильму: " + movieTitle)
                        .call()
                        .content()
        );
    }

    // Fallback — вызывается, если breaker открыт или запрос упал/зависает
    private CompletableFuture<String> fallbackDescription(String movieTitle, Throwable ex) {
        log.warn("Gemini AI недоступен для фильма '{}': {}", movieTitle, ex.getMessage());
        return CompletableFuture.completedFuture(
                "Описание временно недоступно. Попробуйте позже."
        );
    }
}

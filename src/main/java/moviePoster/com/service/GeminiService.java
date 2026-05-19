package moviePoster.com.service;


import moviePoster.com.domain.entity.GenreEntity;
import moviePoster.com.domain.entity.MovieEntity;
import moviePoster.com.dto.response.AiRecommendationResponse;
import moviePoster.com.dto.response.AiReviewResponse;
import moviePoster.com.repository.MovieRepository;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class GeminiService {

    private final MovieRepository movieRepository;
    private final ChatClient chatClient;

    private static final String SYSTEM_PROMPT = """
            Ты — умный ассистент кинотеки MoviePoster.
            Твоя задача — помогать пользователям с выбором фильмов,
            давать обзоры и рекомендации исключительно на тему кино и сериалов.
            
            Правила:
            - Отвечай только на русском языке.
            - Отвечай ТОЛЬКО на вопросы о кино, фильмах, сериалах, актёрах и режиссёрах.
            - Если вопрос не связан с кино — вежливо откажи и предложи задать вопрос о фильмах.
            - Будь лаконичным и дружелюбным.
            """;

    public GeminiService(ChatClient.Builder chatClientBuilder, MovieRepository movieRepository) {
        this.chatClient = chatClientBuilder
                .defaultSystem(SYSTEM_PROMPT)
                .build();
        this.movieRepository = movieRepository;
    }

    /**
     * Обзор фильма — если фильм есть в БД, передаём AI реальные данные
     */
    public AiReviewResponse generateReview(String movieTitle) {
        String prompt;

        var movieOpt = movieRepository.findByName(movieTitle);

        if (movieOpt.isPresent()) {
            MovieEntity movie = movieOpt.get();
            String genres = movie.getGenres().stream()
                    .map(GenreEntity::getName)
                    .collect(Collectors.joining(", "));

            // Передаём AI реальные данные из нашей БД
            prompt = """
                    У нас в кинотеке есть фильм со следующими данными:
                    Название: %s
                    Жанры: %s
                    Описание: %s
                    Дата выхода: %s
                    Возрастной рейтинг: %s+
                    Длительность: %s мин.
                    
                    На основе этих данных напиши краткий обзор.
                    Ответь строго в формате:
                    Жанр: <жанр>
                    Сюжет: <2-3 предложения>
                    Стоит смотреть: <Да/Нет/Любителям жанра> — <одно предложение почему>
                    """.formatted(
                    movie.getName(),
                    genres.isEmpty() ? "не указан" : genres,
                    movie.getDescription() != null ? movie.getDescription() : "не указано",
                    movie.getReleaseDate() != null ? movie.getReleaseDate().toString() : "не указана",
                    movie.getAgeLimit(),
                    movie.getDuration()
            );
        } else {
            // Фильма нет в БД — AI отвечает из своих знаний
            prompt = """
                    Напиши краткий обзор фильма "%s".
                    Ответь строго в формате:
                    Жанр: <жанр>
                    Сюжет: <2-3 предложения>
                    Стоит смотреть: <Да/Нет/Любителям жанра> — <одно предложение почему>
                    """.formatted(movieTitle);
        }

        String raw = callAI(prompt);
        return parseReview(movieTitle, raw);
    }

    //-------------------------------------------------------------------------------

    /**
     * Рекомендации похожих фильмов — с учётом фильмов из нашей БД
     */
    public AiRecommendationResponse recommendSimilar(String movieTitle) {
        // Берём все фильмы из БД чтобы AI мог приоритизировать наши
        List<String> ourMovies = movieRepository.findAll()
                .stream()
                .map(MovieEntity::getName)
                .filter(name -> !name.equalsIgnoreCase(movieTitle))
                .limit(20) // не перегружаем промпт
                .collect(Collectors.toList());

        String prompt;

        if (!ourMovies.isEmpty()) {
            prompt = """
                    Порекомендуй 5 фильмов похожих на "%s".
                    
                    В нашей кинотеке есть следующие фильмы: %s
                    
                    Если среди них есть похожие — отдай им приоритет и упомяни что они есть у нас.
                    Для каждого фильма укажи:
                    - Название
                    - Одно предложение почему стоит посмотреть
                    """.formatted(movieTitle, String.join(", ", ourMovies));
        } else {
            prompt = """
                    Порекомендуй 5 фильмов похожих на "%s".
                    Для каждого укажи название и одно предложение почему стоит посмотреть.
                    """.formatted(movieTitle);
        }

        String raw = callAI(prompt);
        return new AiRecommendationResponse(movieTitle, raw);
    }

    //-------------------------------------------------------------------------------

    /**
     * Свободный вопрос о кино
     */
    public String ask(String question) {
        return callAI(question);
    }

    // --- приватные методы ---

    private String callAI(String userMessage) {
        try {
            return chatClient.prompt()
                    .user(userMessage)
                    .call()
                    .content();
        } catch (Exception e) {
            throw new RuntimeException("Ошибка при обращении к AI: " + e.getMessage(), e);
        }
    }

    //-------------------------------------------------------------------------------

    private AiReviewResponse parseReview(String title, String raw) {
        String genre = extractField(raw, "Жанр:");
        String plot = extractField(raw, "Сюжет:");
        String verdict = extractField(raw, "Стоит смотреть:");
        return new AiReviewResponse(title, genre, plot, verdict, raw);
    }

    //-------------------------------------------------------------------------------

    private String extractField(String text, String label) {
        for (String line : text.split("\n")) {
            if (line.startsWith(label)) {
                return line.substring(label.length()).trim();
            }
        }
        return "";
    }
}
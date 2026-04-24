package moviePoster.com.controller;

import lombok.RequiredArgsConstructor;
import moviePoster.com.dto.request.ReviewRequestDto;
import moviePoster.com.dto.response.ReviewResponseDto;
import moviePoster.com.service.ReviewService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/reviews")
@RequiredArgsConstructor
public class ReviewController {

    private final ReviewService reviewService;

    // Оставить свой отзыв на фильм
    @PostMapping("/{movieName}")
    public ReviewResponseDto addReview(
            @PathVariable String movieName,
            @RequestBody ReviewRequestDto dto) {
        return reviewService.addReview(movieName, dto);
    }

    // Получить все отзывы на фильм
    @GetMapping("/{movieName}")
    public List<ReviewResponseDto> getByMovie(@PathVariable String movieName) {
        return reviewService.getByMovie(movieName);
    }

    // Получить AI-обзор фильма от Gemini
    @GetMapping("/{movieName}/ai")
    public String getAiReview(@PathVariable String movieName) {
        return reviewService.getAiReview(movieName);
    }
}
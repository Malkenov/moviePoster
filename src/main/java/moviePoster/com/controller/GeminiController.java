package moviePoster.com.controller;

import lombok.RequiredArgsConstructor;
import moviePoster.com.dto.response.AiRecommendationResponse;
import moviePoster.com.dto.response.AiReviewResponse;
import moviePoster.com.service.GeminiService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/ai")
@RequiredArgsConstructor
public class GeminiController {

    private final GeminiService geminiService;

    @GetMapping("/review")
    public ResponseEntity<AiReviewResponse> generateReview(@RequestParam String title) {
        return ResponseEntity.ok(geminiService.generateReview(title));
    }

    @GetMapping("/recommend")
    public ResponseEntity<AiRecommendationResponse> recommend(@RequestParam String title) {
        return ResponseEntity.ok(geminiService.recommendSimilar(title));
    }
}
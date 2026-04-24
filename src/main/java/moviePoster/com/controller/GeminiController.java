package moviePoster.com.controller;

import lombok.RequiredArgsConstructor;
import moviePoster.com.service.GeminiService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/ai")
@RequiredArgsConstructor
public class GeminiController {

    private final GeminiService geminiService;

    @GetMapping("/review")
    public String generateReview(@RequestParam String title) {
        return geminiService.generateReview(title);
    }

    @GetMapping("/recommend")
    public String recommend(@RequestParam String title) {
        return geminiService.recommendSimilar(title);
    }
}
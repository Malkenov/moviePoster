package moviePoster.com.controller;

import lombok.RequiredArgsConstructor;
import moviePoster.com.service.GeminiService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/ai/review")
@RequiredArgsConstructor
public class GeminiController {


    private final GeminiService geminiService;

    @PostMapping
    public String generateReview(@RequestBody String title){
        return geminiService.generateReview(title);
    }
}

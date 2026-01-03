package moviePoster.com.controller;

import lombok.RequiredArgsConstructor;
import moviePoster.com.dto.response.AfishaMovieResponse;
import moviePoster.com.service.AfishaService;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;
import java.time.LocalDate;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.format.annotation.DateTimeFormat;



@RestController
@RequestMapping("/api/afisha")
@RequiredArgsConstructor
public class AfishaController {

    private final AfishaService afishaService;

    @GetMapping
    public Page<AfishaMovieResponse> getAfisha(
            @RequestParam(required = false) String city,
            @RequestParam(required = false)
            @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "5") int size
    ) {
        return afishaService.getAfisha(city, date, page, size);
    }

}


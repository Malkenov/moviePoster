package moviePoster.com.controller;

import lombok.RequiredArgsConstructor;
import moviePoster.com.dto.request.MovieRequestDto;
import moviePoster.com.dto.response.MovieResponseDto;
import moviePoster.com.document.MovieDocument;
import moviePoster.com.service.MovieService;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/movies")
@RequiredArgsConstructor
public class MovieController {

    private final MovieService movieService;

    @PostMapping
    public MovieResponseDto create(@RequestBody MovieRequestDto dto) {
        return movieService.create(dto);
    }

    @GetMapping
    public Page<MovieResponseDto> getAll(

            @RequestParam int page,
            @RequestParam int size,
            @RequestParam String sortBy
    ) {
        return movieService.getMovieByPage(page,size,sortBy);
    }

    @GetMapping("/search")
    public Page<MovieDocument> searchMovies (

            @RequestParam String text,
            @RequestParam int page,
            @RequestParam int size
    ) {
        return movieService.searchMovies(text,page,size);
    }

}
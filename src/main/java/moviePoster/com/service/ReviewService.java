package moviePoster.com.service;

import lombok.RequiredArgsConstructor;
import moviePoster.com.domain.entity.MovieEntity;
import moviePoster.com.domain.entity.ReviewEntity;
import moviePoster.com.domain.entity.UserSessionEntity;
import moviePoster.com.dto.request.ReviewRequestDto;
import moviePoster.com.dto.response.ReviewResponseDto;
import moviePoster.com.repository.MovieRepository;
import moviePoster.com.repository.ReviewRepository;
import moviePoster.com.repository.UserRepository;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ReviewService {

    private final ReviewRepository reviewRepository;
    private final MovieRepository movieRepository;
    private final UserRepository userRepository;
    private final GeminiService geminiService;

    public ReviewResponseDto addReview(String movieName, ReviewRequestDto dto) {
        MovieEntity movie = movieRepository.findByName(movieName)
                .orElseThrow(() -> new RuntimeException("Фильм не найден: " + movieName));

        String email = SecurityContextHolder.getContext().getAuthentication().getName();
        UserSessionEntity user = userRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("Пользователь не найден"));

        ReviewEntity review = ReviewEntity.builder()
                .grade(dto.getGrade())
                .title(dto.getTitle())
                .movies(movie)
                .user(user)
                .build();

        reviewRepository.save(review);

        return ReviewResponseDto.builder()
                .grade(review.getGrade())
                .title(review.getTitle())
                .build();
    }

    public String getAiReview(String movieName) {
        movieRepository.findByName(movieName)
                .orElseThrow(() -> new RuntimeException("Фильм не найден: " + movieName));
        return geminiService.generateReview(movieName);
    }

    public List<ReviewResponseDto> getByMovie(String movieName) {
        return reviewRepository.findByMovies_Name(movieName)
                .stream()
                .map(r -> ReviewResponseDto.builder()
                        .grade(r.getGrade())
                        .title(r.getTitle())
                        .build())
                .toList();
    }
}
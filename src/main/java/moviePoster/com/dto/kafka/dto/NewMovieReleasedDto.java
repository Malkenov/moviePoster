package moviePoster.com.dto.kafka.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class NewMovieReleasedDto {

    @NotBlank(message = "Email должен быть заполненным!")
    @Email(message = "Не корректный формат email!")
    private String userEmail;

    @NotBlank(message = "Имя пользователя не может быть пустым!")
    @Size(max = 25,message = "Имя пользователя не должно привышать более 25 символов!")
    private String userName;

    @NotBlank(message = "Название фильма не может быть пустым!")
    private String movieName;

    @NotBlank(message = "Жанр фильма не может быть пустым!")
    private String genre;

    @NotNull(message = "Дата релиза не может быть null!")
    private LocalDate releaseDate;

    @NotBlank(message = "Название кинотеатра не можеь быть пустым!")
    private List<String> cinemaName;
}

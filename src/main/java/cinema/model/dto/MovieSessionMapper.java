package cinema.model.dto;

import java.time.LocalDateTime;
import cinema.model.MovieSession;
import cinema.service.CinemaHallService;
import cinema.service.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class MovieSessionMapper {

    @Autowired
    private MovieService movieService;

    @Autowired
    private CinemaHallService cinemaHallService;

    public MovieSession getMovieSession(MovieSessionRequestDto dto) {
        MovieSession movieSession = new MovieSession();
        movieSession.setCinemaHall(cinemaHallService.getById(dto.getCinemaHallId()));
        movieSession.setMovie(movieService.getById(dto.getMovieId()));
        movieSession.setShowTime(dto.getShowTime());
        return movieSession;
    }

    public MovieSessionResponseDto getMovieSessionResponseDto(MovieSession movieSession) {
        MovieSessionResponseDto dto = new MovieSessionResponseDto();
        dto.setShowTime(movieSession.getShowTime());
        dto.setId(movieSession.getId());
        dto.setCinemaHallId(movieSession.getCinemaHall().getId());
        dto.setMovieId(movieSession.getMovie().getId());
        return dto;
    }
}

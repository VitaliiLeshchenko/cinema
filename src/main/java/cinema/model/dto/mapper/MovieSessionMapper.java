package cinema.model.dto.mapper;

import cinema.model.MovieSession;
import cinema.model.dto.MovieSessionRequestDto;
import cinema.model.dto.MovieSessionResponseDto;
import cinema.service.CinemaHallService;
import cinema.service.MovieService;
import org.springframework.stereotype.Component;

@Component
public class MovieSessionMapper {
    private final MovieService movieService;
    private final CinemaHallService cinemaHallService;

    public MovieSessionMapper(MovieService movieService, CinemaHallService cinemaHallService) {
        this.movieService = movieService;
        this.cinemaHallService = cinemaHallService;
    }

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

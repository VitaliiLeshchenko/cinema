package cinema.model.dto.mapper;

import cinema.model.Movie;
import cinema.model.dto.MovieRequestDto;
import cinema.model.dto.MovieResponseDto;
import org.springframework.stereotype.Component;

@Component
public class MovieMapper {
    public MovieResponseDto getResponseMovieDto(Movie movie) {
        MovieResponseDto dto = new MovieResponseDto();
        dto.setId(movie.getId());
        dto.setTitle(movie.getTitle());
        dto.setDescription(movie.getDescription());
        return dto;
    }

    public Movie getMovie(MovieRequestDto dto) {
        Movie movie = new Movie();
        movie.setTitle(dto.getTitle());
        movie.setDescription(dto.getDescription());
        return movie;
    }
}

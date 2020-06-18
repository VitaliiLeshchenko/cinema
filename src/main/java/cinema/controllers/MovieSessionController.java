package cinema.controllers;

import cinema.model.dto.mapper.MovieSessionMapper;
import cinema.model.dto.MovieSessionRequestDto;
import cinema.model.dto.MovieSessionResponseDto;
import cinema.service.MovieSessionService;
import com.fasterxml.jackson.annotation.JsonFormat;
import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/movie-sessions")
public class MovieSessionController {
    @Autowired
    private MovieSessionMapper movieSessionMapper;
    @Autowired
    private MovieSessionService movieSessionService;

    @PostMapping
    public void addMovieSession(@RequestBody @Valid MovieSessionRequestDto movieSessionRequestDto) {
        movieSessionService.add(movieSessionMapper
                .getMovieSession(movieSessionRequestDto));
    }

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd.MM.yyyy")
    @GetMapping(value = "/available")
    public List<MovieSessionResponseDto> getAllAvailable(@RequestParam Long movieId,
                                                         @RequestParam LocalDate date) {
        return movieSessionService.findAvailableSessions(movieId, date).stream()
                .map(movieSessionMapper::getMovieSessionResponseDto)
                .collect(Collectors.toList());
    }
}

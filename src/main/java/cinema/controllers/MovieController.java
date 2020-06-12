package cinema.controllers;

import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import cinema.model.Movie;
import cinema.model.dto.MovieMapper;
import cinema.model.dto.MovieRequestDto;
import cinema.model.dto.MovieResponseDto;
import cinema.service.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/movies")
public class MovieController {

    @Autowired
    private MovieService movieService;

    @Autowired
    private MovieMapper movieMapper;

    @PostMapping("/add")
    public void add(@RequestBody MovieRequestDto dto) {
        movieService.add(movieMapper.getMovie(dto));
    }

    @GetMapping
    public List<MovieResponseDto> getAll() {
        return movieService.getAll()
                .stream()
                .map(movieMapper::getResponseMovieDto)
                .collect(Collectors.toList());
    }
}

package cinema.controllers;

import java.util.List;
import java.util.stream.Collectors;
import cinema.model.CinemaHall;
import cinema.model.dto.CinemaHallMapper;
import cinema.model.dto.CinemaHallRequestDto;
import cinema.model.dto.CinemaHallResponseDto;
import cinema.service.CinemaHallService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/cinemaHalls")
public class CinemaHallController {
    @Autowired
    private CinemaHallService cinemaHallService;

    @Autowired
    private CinemaHallMapper cinemaHallMapper;

    @PostMapping("/add")
    public void add(@RequestBody CinemaHallRequestDto dto) {
        cinemaHallService.add(cinemaHallMapper.getCinemaHall(dto));
    }

    @GetMapping
    public List<CinemaHallResponseDto> getAll() {
        return cinemaHallService.getAll()
                .stream()
                .map(cinemaHallMapper::getCinemaHallResponseDto)
                .collect(Collectors.toList());
    }
}

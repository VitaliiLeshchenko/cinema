package cinema.service;

import cinema.model.CinemaHall;
import java.util.List;

public interface CinemaHallService extends GenericService<CinemaHall> {
    CinemaHall add(CinemaHall cinemaHall);
    
    List<CinemaHall> getAll();
}

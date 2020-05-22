package cinema.service.impl;

import cinema.dao.CinemaHallDao;
import cinema.model.CinemaHall;
import cinema.service.CinemaHallService;
import cinema.service.Service;
import cinema.util.Inject;
import java.util.List;

@Service
public class CinemaHallServiceImpl implements CinemaHallService {
    @Inject
    private CinemaHallDao cinemaHallDao;

    @Override
    public CinemaHall add(CinemaHall cinemaHall) {
        return cinemaHallDao.add(cinemaHall);
    }

    @Override
    public List<CinemaHall> getAll() {
        return cinemaHallDao.getAll();
    }
}

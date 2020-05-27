package cinema.service.impl;

import cinema.dao.ShoppingCartDao;
import cinema.dao.TicketDao;
import cinema.model.MovieSession;
import cinema.model.ShoppingCart;
import cinema.model.Ticket;
import cinema.model.User;
import cinema.service.Service;
import cinema.service.ShoppingCartService;
import cinema.util.Inject;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class ShoppingCartServiceImpl implements ShoppingCartService {
    @Inject
    private ShoppingCartDao shoppingCartDao;

    @Inject
    private TicketDao ticketDao;

    @Override
    public void addSession(MovieSession movieSession, User user) {
        Ticket ticket = new Ticket(movieSession, user);
        ticketDao.add(ticket);
        ShoppingCart shoppingCart = shoppingCartDao.getByUser(user);
        shoppingCart.getTickets().add(ticket);
        shoppingCartDao.update(shoppingCart);
    }

    @Override
    public ShoppingCart getByUser(User user) {
        return shoppingCartDao.getByUser(user);
    }

    @Override
    public void registerNewShoppingCart(User user) {
        ShoppingCart shoppingCart = new ShoppingCart(List.of(), LocalDateTime.now(), user);
        shoppingCartDao.add(shoppingCart);
    }
}

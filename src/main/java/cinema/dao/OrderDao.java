package cinema.dao;

import cinema.model.Orders;
import cinema.model.Ticket;
import cinema.model.User;
import java.util.List;

public interface OrderDao {
    Orders add(List<Ticket> tickets, User user);

    List<Orders> getByUSer(User user);
}

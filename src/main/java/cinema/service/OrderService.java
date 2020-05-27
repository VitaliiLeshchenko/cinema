package cinema.service;

import cinema.model.Orders;
import cinema.model.Ticket;
import cinema.model.User;
import java.util.List;

public interface OrderService {
    Orders completeOrder(List<Ticket> tickets, User user);
            
    List<Orders> getOrderHistory(User user);
}

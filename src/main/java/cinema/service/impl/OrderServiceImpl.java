package cinema.service.impl;

import cinema.dao.OrderDao;
import cinema.model.Order;
import cinema.model.Ticket;
import cinema.model.User;
import cinema.service.OrderService;
import java.time.LocalDateTime;
import java.util.List;
import org.springframework.stereotype.Service;

@Service
public class OrderServiceImpl implements OrderService {
    private final OrderDao orderDao;

    public OrderServiceImpl(OrderDao orderDao) {
        this.orderDao = orderDao;
    }

    @Override
    public Order completeOrder(List<Ticket> tickets, User user) {
        return orderDao.add(new Order(tickets, LocalDateTime.now(), user));
    }

    @Override
    public List<Order> getOrderHistory(User user) {
        return orderDao.getByUser(user);
    }
}

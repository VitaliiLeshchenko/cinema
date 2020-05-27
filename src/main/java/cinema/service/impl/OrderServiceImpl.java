package cinema.service.impl;

import cinema.dao.OrderDao;
import cinema.model.Orders;
import cinema.model.Ticket;
import cinema.model.User;
import cinema.service.OrderService;
import cinema.service.Service;
import cinema.util.Inject;
import java.util.List;

@Service
public class OrderServiceImpl implements OrderService {
    @Inject
    private OrderDao orderDao;

    @Override
    public Orders completeOrder(List<Ticket> tickets, User user) {
        return orderDao.add(tickets, user);
    }

    @Override
    public List<Orders> getOrderHistory(User user) {
        return orderDao.getByUSer(user);
    }
}

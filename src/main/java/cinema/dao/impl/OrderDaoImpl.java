package cinema.dao.impl;

import cinema.dao.OrderDao;
import cinema.exception.DataProcessingException;
import cinema.model.Order;
import cinema.model.User;
import java.util.List;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;

@Repository
public class OrderDaoImpl implements OrderDao {
    private final SessionFactory sessionFactory;

    public OrderDaoImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public Order add(Order order) {
        Transaction transaction = null;
        try (Session session = sessionFactory.openSession()) {
            transaction = session.beginTransaction();
            session.save(order);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            throw new DataProcessingException("Can't create new order", e);
        }
        return null;
    }

    public List<Order> getByUser(User user) {
        try (Session session = sessionFactory.openSession()) {
            Query<Order> query = session.createQuery("SELECT DISTINCT o FROM Order o "
                    + "LEFT JOIN FETCH o.tickets t "
                    + "JOIN FETCH t.movieSession m "
                    + "JOIN FETCH t.user u "
                    + "JOIN FETCH m.cinemaHall "
                    + "JOIN FETCH m.movie "
                    + "WHERE o.user.id = :userId", Order.class);
            query.setParameter("userId", user.getId());
            return query.getResultList();
        } catch (Exception e) {
            throw new DataProcessingException("Can't find orders by User :" + user, e);
        }
    }
}

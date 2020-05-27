package cinema.dao.impl;

import cinema.dao.Dao;
import cinema.dao.OrderDao;
import cinema.exception.DataProcessingException;
import cinema.model.Orders;
import cinema.model.Ticket;
import cinema.model.User;
import cinema.util.HibernateUtil;
import java.time.LocalDateTime;
import java.util.List;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

@Dao
public class OrderDaoImpl implements OrderDao {
    @Override
    public Orders add(List<Ticket> tickets, User user) {
        Transaction transaction = null;
        Session session = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            transaction = session.beginTransaction();
            Orders orders = new Orders(tickets, LocalDateTime.now(), user);
            session.save(orders);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            throw new DataProcessingException("Can't create new order", e);
        } finally {
            if (session != null) {
                session.close();
            }
        }
        return null;
    }

    @Override
    public List<Orders> getByUSer(User user) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Query<Orders> query = session.createQuery("SELECT DISTINCT o FROM Orders o "
                    + "LEFT JOIN FETCH o.tickets "
                    + "JOIN FETCH o.user "
                    + "WHERE o.user.id = :userId", Orders.class);
            query.setParameter("userId", user.getId());
            return query.getResultList();
        } catch (Exception e) {
            throw new DataProcessingException("Can't find orders by User :" + user, e);
        }
    }
}

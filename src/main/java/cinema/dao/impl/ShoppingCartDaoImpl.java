package cinema.dao.impl;

import cinema.dao.ShoppingCartDao;
import cinema.exception.DataProcessingException;
import cinema.model.ShoppingCart;
import cinema.model.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;

@Repository
public class ShoppingCartDaoImpl implements ShoppingCartDao {
    private final SessionFactory sessionFactory;

    public ShoppingCartDaoImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public ShoppingCart add(ShoppingCart shoppingCart) {
        Transaction transaction = null;
        try (Session session = sessionFactory.openSession()) {
            transaction = session.beginTransaction();
            session.save(shoppingCart);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            throw new DataProcessingException("Can't save shoppingCart : " + shoppingCart, e);
        }
        return shoppingCart;
    }

    public ShoppingCart getByUser(User user) {
        try (Session session = sessionFactory.openSession()) {
            Query<ShoppingCart> query = session.createQuery(
                    "FROM ShoppingCart sc "
                            + "LEFT JOIN FETCH sc.tickets t "
                            + "LEFT JOIN FETCH t.movieSession m "
                            + "LEFT JOIN FETCH m.movie "
                            + "LEFT JOIN FETCH m.cinemaHall "
                            + "LEFT JOIN FETCH t.user u "
                            + "where sc.user.id = :userId", ShoppingCart.class);
            query.setParameter("userId", user.getId());
            return query.getSingleResult();
        } catch (Exception e) {
            throw new DataProcessingException("Can't get shoppingCart by User", e);
        }
    }

    public void update(ShoppingCart shoppingCart) {
        Transaction transaction = null;
        try (Session session = sessionFactory.openSession()) {
            transaction = session.beginTransaction();
            session.update(shoppingCart);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            throw new DataProcessingException("Can't update shoppingCart : " + shoppingCart, e);
        }
    }
}

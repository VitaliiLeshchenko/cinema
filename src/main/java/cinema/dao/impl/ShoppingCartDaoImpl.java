package cinema.dao.impl;

import cinema.dao.Dao;
import cinema.dao.ShoppingCartDao;
import cinema.exception.DataProcessingException;
import cinema.model.ShoppingCart;
import cinema.model.User;
import cinema.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

@Dao
public class ShoppingCartDaoImpl implements ShoppingCartDao {
    @Override
    public ShoppingCart add(ShoppingCart shoppingCart) {
        Session session = null;
        Transaction transaction = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            transaction = session.beginTransaction();
            session.save(shoppingCart);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            throw new DataProcessingException("Can't save shoppingCart : " + shoppingCart, e);
        } finally {
            if (session != null) {
                session.close();
            }
        }
        return shoppingCart;
    }

    @Override
    public ShoppingCart getByUser(User user) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Query<ShoppingCart> query = session.createQuery(
                    "FROM ShoppingCart sc "
                            + "LEFT JOIN FETCH sc.tickets t "
                            + "LEFT JOIN FETCH t.movieSession m "
                            + "LEFT JOIN FETCH m.movie "
                            + "LEFT JOIN FETCH m.cinemaHall "
                            + "LEFT JOIN FETCH t.user u "
                            + "where sc.user.id = :userId", ShoppingCart.class);
            query.setParameter("userId", user.getId());
            ShoppingCart shoppingCart = query.getSingleResult();
            return shoppingCart;
        } catch (Exception e) {
            throw new DataProcessingException("Can't get shoppingCart by User", e);
        }
    }

    @Override
    public void update(ShoppingCart shoppingCart) {
        Session session = null;
        Transaction transaction = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            transaction = session.beginTransaction();
            session.update(shoppingCart);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            throw new DataProcessingException("Can't update shoppingCart : " + shoppingCart, e);
        } finally {
            if (session != null) {
                session.close();
            }
        }
    }
}

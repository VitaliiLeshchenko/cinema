package cinema.dao.impl;

import cinema.dao.CinemaHallDao;
import cinema.exception.DataProcessingException;
import cinema.model.CinemaHall;
import java.util.List;
import javax.persistence.criteria.CriteriaQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;

@Repository
public class CinemaHallDaoImpl implements CinemaHallDao {
    private final SessionFactory sessionFactory;

    public CinemaHallDaoImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public CinemaHall add(CinemaHall cinemaHall) {
        Transaction transaction = null;
        try (Session session = sessionFactory.openSession()) {
            transaction = session.beginTransaction();
            cinemaHall.setId((Long) session.save(cinemaHall));
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            throw new DataProcessingException("Can't create CinemaHall: " + cinemaHall, e);
        }
        return cinemaHall;
    }

    public List<CinemaHall> getAll() {
        try (Session session = sessionFactory.openSession()) {
            CriteriaQuery<CinemaHall> criteriaQuery = session
                    .getCriteriaBuilder().createQuery(CinemaHall.class);
            criteriaQuery.from(CinemaHall.class);
            return session.createQuery(criteriaQuery).getResultList();
        } catch (Exception e) {
            throw new DataProcessingException("Can't get all CinemaHalls", e);
        }
    }

    public CinemaHall getById(Long id) {
        try (Session session = sessionFactory.openSession()) {
            Query<CinemaHall> query = session.createQuery(
                    "FROM CinemaHall WHERE id = :id", CinemaHall.class);
            query.setParameter("id", id);
            return query.getSingleResult();
        } catch (Exception e) {
            throw new DataProcessingException("Can't find cinemaHall with id: " + id, e);
        }
    }
}

package cinema.dao.impl;

import cinema.dao.MovieSessionDao;
import cinema.exception.DataProcessingException;
import cinema.model.MovieSession;
import java.time.LocalDate;
import java.util.List;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.JoinType;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;

@Repository
public class MovieSessionDaoImpl implements MovieSessionDao {
    private final SessionFactory sessionFactory;

    public MovieSessionDaoImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public MovieSession add(MovieSession movieSession) {
        Transaction transaction = null;
        try (Session session = sessionFactory.openSession()) {
            transaction = session.beginTransaction();
            movieSession.setId((Long) session.save(movieSession));
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            throw new DataProcessingException("Can't create MovieSession: " + movieSession, e);
        }
        return movieSession;
    }

    public List<MovieSession> findAvailableSessions(Long movieId, LocalDate date) {
        try (Session session = sessionFactory.openSession()) {
            CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
            CriteriaQuery<MovieSession> criteriaQuery = criteriaBuilder
                    .createQuery(MovieSession.class);
            Root<MovieSession> root = criteriaQuery.from(MovieSession.class);
            root.fetch("movie", JoinType.INNER);
            root.fetch("cinemaHall", JoinType.INNER);
            Predicate predicateMovieId = criteriaBuilder.equal(root.get("movie"), movieId);
            Predicate predicateDate = criteriaBuilder.between(root.get("showTime"),
                    date.atStartOfDay(), date.plusDays(1L).atStartOfDay());
            criteriaQuery.where(predicateMovieId, predicateDate);
            return session.createQuery(criteriaQuery).getResultList();
        } catch (Exception e) {
            throw new DataProcessingException("Can't get all movieSessions", e);
        }
    }

    public MovieSession getById(Long id) {
        try (Session session = sessionFactory.openSession()) {
            Query<MovieSession> query = session.createQuery("SELECT m FROM MovieSession m "
                    + "JOIN FETCH m.cinemaHall "
                    + "JOIN FETCH m.movie "
                    + "WHERE m.id = :id", MovieSession.class);
            query.setParameter("id", id);
            return query.getSingleResult();
        } catch (Exception e) {
            throw new DataProcessingException("Can't find movieSession by id : " + id, e);
        }
    }

    public List<MovieSession> getAll() {
        try (Session session = sessionFactory.openSession()) {
            Query<MovieSession> query = session.createQuery("FROM MovieSession",
                    MovieSession.class);
            return query.getResultList();
        } catch (Exception e) {
            throw new DataProcessingException("Can't find movieSessions at all", e);
        }
    }
}

package cinema.dao.impl;

import cinema.dao.MovieDao;
import cinema.exception.DataProcessingException;
import cinema.model.Movie;
import java.util.List;
import javax.persistence.criteria.CriteriaQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;

@Repository
public class MovieDaoImpl implements MovieDao {
    private final SessionFactory sessionFactory;

    public MovieDaoImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public Movie add(Movie movie) {
        Transaction transaction = null;
        try (Session session = sessionFactory.openSession()) {
            transaction = session.beginTransaction();
            movie.setId((Long) session.save(movie));
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            throw new DataProcessingException("Can't create movie: " + movie, e);
        }
        return movie;
    }

    public List<Movie> getAll() {
        try (Session session = sessionFactory.openSession()) {
            CriteriaQuery<Movie> criteriaQuery = session
                    .getCriteriaBuilder().createQuery(Movie.class);
            criteriaQuery.from(Movie.class);
            return session.createQuery(criteriaQuery).getResultList();
        } catch (Exception e) {
            throw new DataProcessingException("Can't get all movies", e);
        }
    }

    public Movie getById(Long id) {
        try (Session session = sessionFactory.openSession()) {
            Query<Movie> query = session.createQuery("FROM Movie WHERE id = :id", Movie.class);
            query.setParameter("id", id);
            return query.getSingleResult();
        } catch (Exception e) {
            throw new DataProcessingException("Can't find movie with id: " + id, e);
        }
    }
}

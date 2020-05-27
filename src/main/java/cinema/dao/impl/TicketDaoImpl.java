package cinema.dao.impl;

import cinema.dao.Dao;
import cinema.dao.TicketDao;
import cinema.exception.DataProcessingException;
import cinema.model.Ticket;
import cinema.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;

@Dao
public class TicketDaoImpl implements TicketDao {
    @Override
    public Ticket add(Ticket ticket) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            session.save(ticket);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            throw new DataProcessingException("Can't save ticket : " + ticket, e);
        }
        return ticket;
    }
}

package cinema.dao.impl;

import cinema.dao.TicketDao;
import cinema.exception.DataProcessingException;
import cinema.model.Ticket;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class TicketDaoImpl implements TicketDao {
    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public Ticket add(Ticket ticket) {
        Session session = null;
        Transaction transaction = null;
        try {
            session = sessionFactory.openSession();
            transaction = session.beginTransaction();
            session.save(ticket);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            throw new DataProcessingException("Can't save ticket : " + ticket, e);
        } finally {
            if (session != null) {
                session.close();
            }
        }
        return ticket;
    }
}

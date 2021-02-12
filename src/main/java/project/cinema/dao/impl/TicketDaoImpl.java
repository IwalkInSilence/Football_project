package project.cinema.dao.impl;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.stereotype.Repository;
import project.cinema.dao.TicketDao;
import project.cinema.exception.DataProcessingException;
import project.cinema.model.Ticket;

@Repository
public class TicketDaoImpl implements TicketDao {
    private final SessionFactory sessionFactory;

    public TicketDaoImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public Ticket add(Ticket ticket) {
        Transaction transaction = null;
        Session session = null;
        try {
            session = sessionFactory.openSession();
            transaction = session.beginTransaction();
            session.save(ticket);
            transaction.commit();
            return ticket;
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            throw new DataProcessingException("Can't insert ticket: " + ticket + " in DB", e);
        } finally {
            if (session != null) {
                session.close();
            }
        }
    }
}

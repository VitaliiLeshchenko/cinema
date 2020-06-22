package cinema.dao.impl;

import cinema.dao.RoleDao;
import cinema.exception.DataProcessingException;
import cinema.model.Role;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;

@Repository
public class RoleDaoImpl implements RoleDao {
    private final SessionFactory sessionFactory;

    public RoleDaoImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public Role add(Role role) {
        Transaction transaction = null;
        try (Session session = sessionFactory.openSession()) {
            transaction = session.beginTransaction();
            session.save(role);
            transaction.commit();
            return role;
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            throw new DataProcessingException("Can't insert Role entity", e);
        }
    }

    public Role getRoleByName(String roleName) {
        try (Session session = sessionFactory.openSession()) {
            String hql = "FROM Role R WHERE R.roleName = :roleName";
            Query<Role> query = session.createQuery(hql, Role.class);
            query.setParameter("roleName", roleName);
            return query.uniqueResult();
        } catch (Exception e) {
            throw new DataProcessingException("Can't find Role", e);
        }
    }
}

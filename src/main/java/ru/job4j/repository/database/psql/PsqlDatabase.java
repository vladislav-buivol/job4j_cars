package ru.job4j.repository.database.psql;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import ru.job4j.model.EntityModel;
import ru.job4j.repository.Database;

import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;
import java.util.function.Function;

public class PsqlDatabase<T extends EntityModel> implements Database<T> {

    private final Class<T> clazz;

    public PsqlDatabase(Class<T> t) {
        this.clazz = t;
    }

    @Override
    public <T> T execute(final Function<Session, T> command) {
        final Session session = PsqlConnectionManager.instOf().getSf().openSession();
        return execute(command, session);
    }

    private <T> T execute(Function<Session, T> command, Session session) {
        final Transaction tx = session.beginTransaction();
        try {
            T rsl = command.apply(session);
            tx.commit();
            return rsl;
        } catch (final Exception e) {
            session.getTransaction().rollback();
            throw e;
        } finally {
            session.close();
        }
    }

    @Override
    public T add(T t) throws SQLException {
        execute(session -> {
            Integer id = (Integer) session.save(t);
            t.setId(id);
            return t;
        });
        return null;
    }

    @Override
    public boolean update(String id, T t) {
        execute(session -> {
            session.merge(t);
            return true;
        });
        return false;
    }

    @Override
    public boolean delete(String id) {
        return execute(session -> {
            try {
                session.remove(findById(id));
                return true;
            } catch (Exception e) {
                e.printStackTrace();
                System.out.println(e.getMessage());
            }
            return false;
        });
    }

    @Override
    public List<T> findAll() {
        final Session session = PsqlConnectionManager.instOf().getSf().openSession();
        CriteriaQuery<T> query = session.getCriteriaBuilder().createQuery(clazz);
        Root<T> root = query.from(clazz);
        query.select(root);
        return execute(sess -> sess.createQuery(query).list(), session);
    }

    @Override
    public T findById(String id) {
        return execute(session -> session.get(clazz, Integer.parseInt(id)));
    }

    @Override
    public List<T> executeSelect(String query, Map<String, Object> params) {
        return execute(session -> rawQuery(query, params, session).list());
    }

    @Override
    public boolean executeUpdate(String query, Map<String, Object> params) {
        return this
                .execute(session -> rawQuery(query, params, session).executeUpdate() == 1);
    }

    private Query rawQuery(String query, Map<String, Object> params, Session session) {
        Query q = session.createQuery(query);
        for (String key : params.keySet()) {
            q.setParameter(key, params.get(key));
        }
        return q;
    }

}

package model;

import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public abstract class DAOImplHibernate<T, K> implements DAO<T, K> {

    protected SessionFactory sessionFactory;
    protected Class<T> hclass;

    public DAOImplHibernate() {

    }

    private boolean accion(Accionable<T> a) {
        Session session = this.sessionFactory.openSession();
        try {
            session.beginTransaction();
            a.accion(session);
            session.getTransaction().commit();
            session.close();
            return false;
        } catch (Exception e) {
            e.printStackTrace();
        }
        session.close();
        return true;
    }

    @Override
    public boolean guardar(T objeto) {
        return accion(session -> session.save(objeto));
    }

    @Override
    public boolean actualizar(T objeto) {
        return accion(session -> session.update(objeto));
    }

    @Override
    public boolean borrar(T objeto) {
        return accion(session -> session.delete(objeto));
    }

    @Override
    public T encontrar(K llave) {
        Session session = this.sessionFactory.openSession();
        try {
            session.beginTransaction();
            T object = session.find(hclass, llave);
            session.getTransaction().commit();
            session.close();
            return object;
        } catch (Exception e) {
            e.printStackTrace();
        }
        session.close();
        return null;
    }

    @Override
    public List<T> encontrarTodos() {
        Session session = this.sessionFactory.openSession();
        try {
            session.beginTransaction();
            CriteriaBuilder builder = session.getCriteriaBuilder();
            CriteriaQuery<T> criteria = builder.createQuery(hclass);
            criteria.from(hclass);
            List<T> list = session.createQuery(criteria).getResultList();
            session.close();
            return list;
        } catch (Exception e) {
            e.printStackTrace();
        }
        session.close();
        return null;
    }
}

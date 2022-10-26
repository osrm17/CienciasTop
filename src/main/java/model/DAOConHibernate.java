package model;

import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import extra.Accionable;
import extra.Tupla;

/**
 * Clase abstracta generica que implementa la interfaz DAO<T, K>.
 * Esta clase proporciona una implementacion del patron DAO usando hibernate.
 * 
 * Los atributos de esta clase deben ser instanciados por la clase
 * que extiende.
 * 
 * @version 1.0
 * 
 * @param T -- tipo de los objetos(modelo) con el que se va operar.
 * @param K -- tipo de la llave de los objetos con los que se va operar.
 * 
 */
public abstract class DAOConHibernate<T, K> implements DAO<T, K> {

    protected SessionFactory sessionFactory;
    protected Class<T> hclass;

    /**
     * Metodo privado parar realizar una accion sencilla de acceso.
     * 
     * @param a -- accionable, una implementacion concreta de la accion a realizar.
     * @return Tupla -- con los posibles valores que devuelve una accion, la primera
     *         entrada tiene un booleano para el caso en el que solo requerimos
     *         saber si la operacion fue exitosa y en la segunda entrada tenemos
     *         un objeto de tipo T en caso de que la accion devuelva un objeto.
     */
    private Tupla<Boolean, T> accion(Accionable<T> a) {
        Tupla<Boolean, T> tupla = new Tupla<Boolean, T>(false, null);
        Session session = this.sessionFactory.openSession();
        try {
            session.beginTransaction();
            tupla.setSegundo(a.accion(session));
            session.getTransaction().commit();
            session.close();
            return tupla;
        } catch (Exception e) {
            e.printStackTrace();
        }
        tupla.setPrimero(true);
        tupla.setSegundo(null);
        session.close();
        return tupla;
    }

    @Override
    public boolean guardar(T objeto) {
        return accion(session -> {
            session.save(objeto);
            return null;
        }).getPrimero();
    }

    @Override
    public boolean actualizar(T objeto) {
        return accion(session -> {
            session.update(objeto);
            return null;
        }).getPrimero();
    }

    @Override
    public boolean borrar(T objeto) {
        return accion(session -> {
            session.delete(objeto);
            return null;
        }).getPrimero();
    }

    @Override
    public T encontrar(K llave) {
        return accion(session -> session.find(hclass, llave)).getSegundo();
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

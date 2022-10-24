package model;

import org.hibernate.cfg.Configuration;

/**
 * Clase concreta que extiende a DAOConHibernate<Existencia, Integer> e
 * implementa ExistenciaDAO.
 * 
 * @version 1.0
 */
public class ExistenciaDAOHibernate extends DAOConHibernate<Existencia, Integer> implements ExistenciaDAO {

    /**
     * Constructor sin parametros de un objeto DAO usando hibernate, para acceder a
     * existencias.
     */
    public ExistenciaDAOHibernate() {
        super();
        this.sessionFactory = new Configuration().configure("hibernate.cfg.xml")
                .addAnnotatedClass(Existencia.class).buildSessionFactory();
        this.hclass = Existencia.class;
    }
}

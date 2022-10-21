package model;

import org.hibernate.cfg.Configuration;

/**
 * Clase concreta que extiende a DAOImplHibernate<Usuario, String> e implementa
 * UsuarioDAO.
 * 
 * @version 1.0
 */
public class UsuarioDAOImplHibernate extends DAOImplHibernate<Usuario, String> implements UsuarioDAO {

    /**
     * Constructor sin parametros de un objeto DAO para usuarios usando hibernate.
     */
    public UsuarioDAOImplHibernate() {
        super();
        this.sessionFactory = new Configuration().configure("hibernate.cfg.xml")
                .addAnnotatedClass(Usuario.class).buildSessionFactory();
        this.hclass = Usuario.class;
    }

}

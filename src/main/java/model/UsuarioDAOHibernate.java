package model;

import org.hibernate.cfg.Configuration;

/**
 * Clase concreta que extiende a DAOConHibernate<Usuario, String> e implementa
 * UsuarioDAO.
 * 
 * @version 1.0
 */
public class UsuarioDAOHibernate extends DAOConHibernate<Usuario, String> implements UsuarioDAO {

    /**
     * Constructor sin parametros de un objeto DAO usando hibernate, para acceder a
     * usuarios.
     */
    public UsuarioDAOHibernate() {
        this.sessionFactory = new Configuration().configure("hibernate.cfg.xml")
                .addAnnotatedClass(Usuario.class).buildSessionFactory();
        this.hclass = Usuario.class;
    }

}

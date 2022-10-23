package model;

import org.hibernate.cfg.Configuration;

/**
 * Clase concreta que extiende a DAOImplHibernate<Usuario, String> e implementa
 * UsuarioDAO.
 * 
 * @version 1.0
 */
public class ProductoDAOImplHibernate extends DAOImplHibernate<Producto, String> implements ProductoDAO {

    /**
     * Constructor sin parametros de un objeto DAO usando hibernate, para acceder a
     * usuarios.
     */
    public ProductoDAOImplHibernate() {
        super();
        this.sessionFactory = new Configuration().configure("hibernate.cfg.xml")
                .addAnnotatedClass(Producto.class).buildSessionFactory();
        this.hclass = Producto.class;
    }

}

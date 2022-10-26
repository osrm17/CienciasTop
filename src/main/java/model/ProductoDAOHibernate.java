package model;

import org.hibernate.cfg.Configuration;

/**
 * Clase concreta que extiende a DAOConHibernate<Producto, String> e implementa
 * UsuarioDAO.
 * 
 * @version 1.0
 */
public class ProductoDAOHibernate extends DAOConHibernate<Producto, String> implements ProductoDAO {

    /**
     * Constructor sin parametros de un objeto DAO usando hibernate, para acceder a
     * productos.
     */
    public ProductoDAOHibernate() {
        this.sessionFactory = new Configuration().configure("hibernate.cfg.xml")
                .addAnnotatedClass(Producto.class).buildSessionFactory();
        this.hclass = Producto.class;
    }

}

package model;

import org.hibernate.cfg.Configuration;

public class UsuarioDAOImplHibernate extends DAOImplHibernate<Usuario, String> implements UsuarioDAO {

    public UsuarioDAOImplHibernate() {
        super();
        this.sessionFactory = new Configuration().configure("hibernate.cfg.xml")
                .addAnnotatedClass(Usuario.class).buildSessionFactory();
        this.hclass = Usuario.class;
    }

}

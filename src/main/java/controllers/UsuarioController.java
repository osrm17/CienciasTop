package controllers;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import model.Usuario;

public class UsuarioController {

    public String add(Usuario usuario) {
        SessionFactory sessionFactory = new Configuration().configure("hibernate.cfg.xml")
                .addAnnotatedClass(Usuario.class).buildSessionFactory();
        Session session = sessionFactory.openSession();
        try {
            
            session.beginTransaction();
            session.save(usuario);
            session.getTransaction().commit();
            return "chulada";
        } catch (Exception e) {
        }
        session.close();
        return "mamut";
    }

}

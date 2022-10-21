package model;

import org.hibernate.Session;

@FunctionalInterface
public interface Accionable<T> {

    public void accion(Session session);
}

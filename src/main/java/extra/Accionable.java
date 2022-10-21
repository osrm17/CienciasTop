package extra;

import org.hibernate.Session;

/**
 * Interfaz funcional para definir el comportamiento de un accionable
 * que realiza una operacion sencilla con hibernate que requiera una sesion y
 * posiblemente devuelva un objeto
 * 
 * @param T -- tipo de los objetos(modelo) con el que se va operar.
 * @version 1.0
 */
@FunctionalInterface
public interface Accionable<T> {

    /**
     * Metodo que realiza una accion utilizando una sesion de hibernate.
     * 
     * @param session -- sesion de hibernate.
     */
    public T accion(Session session);
}

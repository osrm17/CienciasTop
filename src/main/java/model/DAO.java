package model;

import java.util.List;

/**
 * Interfaz generica que defiene el comportamiento escencial definido 
 * por el patron de disenio Data Acces Object, 
 * para acceder a la informacion de la base de datos.
 * 
 * @param T -- tipo de los objetos(modelo) con el que se va operar.
 * @param K -- tipo de la llave de los objetos con los que se va operar.
 * 
 * @version 1.0
 */
public interface DAO<T, K> {

    /**
     * Guarda un objeto en la base de datos.
     * 
     * @param objeto -- objeto que se guardara.
     * @return boolean -- verdadero en caso de haya ocurrido un error
     *         durante esta operacion y falso en caso contrario.
     */
    public boolean guardar(T objeto);

    /**
     * Actualiza un objeto de la base de datos.
     * 
     * @param objeto -- objeto que se actualizara.
     * @return boolean -- verdadero en caso de haya ocurrido un error
     *         durante esta operacion y falso en caso contrario.
     */
    public boolean actualizar(T objeto);

    /**
     * Elimina un objeto de la base de datos.
     * 
     * @param objeto -- objeto que se eliminara.
     * @return boolean -- verdadero en caso de haya ocurrido un error
     *         durante esta operacion y falso en caso contrario.
     */
    public boolean borrar(T objeto);

    /**
     * Devuelve el objeto con la llave especificada.
     * 
     * @param llave -- llave primaria del objeto que se quiere obtener.
     * @return T -- El objeto con la llave especificada o null en caso
     *         de que no exista tal objeto.
     */
    public T encontrar(K llave);

    /**
     * Devuelve una lista con todos los objetos de un mismo modelo.
     * 
     * @return List -- lista con todos los objetos o null en caso de error
     *         durante la operacion.
     */
    public List<T> encontrarTodos();
}
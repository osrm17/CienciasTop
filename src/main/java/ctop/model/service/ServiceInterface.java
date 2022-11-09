package ctop.model.service;

/**
 * Interfaz generica que define implementacion que debe tener
 * un servicio que maneja entidades en la base de datos.
 *
 * @param <T> Tipo de la entidad que maneja el servicio.
 * @param <K> Tipo de la llave de la entidad <T> .
 * @version 1.0
 */
public interface ServiceInterface<T, K> {

    /**
     * Devuelve todos los registros disponibles en la
     * base de datos.
     * 
     * @return Iterable<T>
     */
    public Iterable<T> findAll();

    /**
     * Devuelve una entidad dada su llave.
     * 
     * @param llave con el que se identifica al producto
     * @return T -- entidad asociada al codigo en la base de datos.
     */
    public T findById(K llave);

    /**
     * Guarda la entidad pasada como parametro en la base de datos y
     * devuelve esta misma.
     * 
     * @param entidad que se agrega a la base de datos.
     * @return T -- entidad agregada a la base de datos.
     */
    public T save(T entidad);

    /**
     * Borra una entidad de la base de datos dada su llave.
     * 
     * @param llave de la entidad a borrar.
     */
    public void delete(K llave);
}

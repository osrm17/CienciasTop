package ctop.model.service;

import ctop.model.entity.Carrera;

/**
 * Interfaz que define implementacion que debe tener
 * un servicio que maneja las carreras.
 * 
 * @version 1.0
 */
public interface CarreraServiceInterface {

    /**
     * Metodo que devuelve todas las carreras disponibles en la
     * base de datos.
     * 
     * @return Iterable-Carrera -- devuelve un iterable de carreras.
     */
    public Iterable<Carrera> findAll();
}

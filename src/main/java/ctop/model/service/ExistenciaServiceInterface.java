package ctop.model.service;

import ctop.model.entity.Existencia;

/**
 * Interfaz que define implementacion que debe tener
 * un servicio que maneja las existencias.
 * 
 * @version 1.0
 */
public interface ExistenciaServiceInterface {

    /**
     * Metodo que devuelve todas las existencias disponibles en la
     * base de datos.
     * 
     * @return Iterable-Existencia -- devuelve un iterable de existencias.
     */
    public Iterable<Existencia> findAll();
}

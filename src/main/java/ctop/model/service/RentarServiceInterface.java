package ctop.model.service;

import ctop.model.entity.Rentar;

/**
 * Interfaz que define la implementacion que debe tener
 * un servicio que maneja las rentas.
 * 
 * @version 1.0
 */
public interface RentarServiceInterface {

    /**
     * Metodo que devuelve todas las rentas disponibles en la
     * base de datos.
     * 
     * @return Iterable-Usuario -- devuelve un iterable de productos.
     */
    public Iterable<Rentar> findAll();
}
